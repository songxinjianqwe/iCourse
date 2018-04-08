package com.sinjinsong.icourse.core.service.pay.impl;

import com.sinjinsong.icourse.core.dao.pay.AlipayDOMapper;
import com.sinjinsong.icourse.core.domain.dto.pay.AccountResult;
import com.sinjinsong.icourse.core.domain.dto.pay.PayDTO;
import com.sinjinsong.icourse.core.domain.entity.course.CourseDO;
import com.sinjinsong.icourse.core.domain.entity.course.StudyRecordDO;
import com.sinjinsong.icourse.core.domain.entity.manager.ManagerDO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.pay.AlipayDO;
import com.sinjinsong.icourse.core.enumeration.course.StudyStatus;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.core.exception.order.OrderNotFoundException;
import com.sinjinsong.icourse.core.exception.order.OrderStateIllegalException;
import com.sinjinsong.icourse.core.exception.pay.*;
import com.sinjinsong.icourse.core.properties.RefundProperties;
import com.sinjinsong.icourse.core.service.course.CourseService;
import com.sinjinsong.icourse.core.service.course.StudyService;
import com.sinjinsong.icourse.core.service.manager.ManagerService;
import com.sinjinsong.icourse.core.service.order.OrderService;
import com.sinjinsong.icourse.core.service.pay.AccountService;
import com.sinjinsong.icourse.core.service.student.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AlipayDOMapper alipayDOMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ManagerService managerService;


    @Transactional
    @Override
    public AccountResult bind(Long userId, PayDTO payDTO) {
        if (alipayDOMapper.findByUserId(userId) != null) {
            throw new UserBindMultiAccountException(userId);
        }
        AlipayDO account = alipayDOMapper.selectByPrimaryKey(payDTO.getAlipayUsername());
        if (account != null && passwordEncoder.matches(payDTO.getPaymentPassword(), account.getPaymentPassword())) {
            if (account.getUserId() == null) {
                account.setUserId(userId);
                alipayDOMapper.updateByPrimaryKeySelective(account);
                return new AccountResult(true, account.getBalance());
            } else {
                log.info("{} 用户支付账号已被绑定", account);
                throw new AccountAlreadyBoundException(userId);
            }
        } else {
            log.info("{} 用户支付账号、密码不匹配", userId);
            throw new AccountUsernameOrPasswordInCorrectException(userId);
        }
    }

    @Transactional
    @Override
    public OrderDO pay(Long orderId, PayDTO payDTO) {
        // 如果支付账号、密码匹配，并且余额足够支持，那么
        // 扣除用户余额
        // 订单状态设置为已支付
        // 学生的消费金额增加，VIP等级调整
        // 在选课表中添加一条记录，状态为已选课
        // 订单和选课记录是的一对一的关系
        OrderDO order = orderService.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(String.valueOf(orderId));
        }
        if (order.getStatus() != OrderStatus.UNPAID) {
            throw new OrderStateIllegalException(order.getStatus().toString());
        }
        Long userId = order.getStudent().getId();
        AlipayDO account = alipayDOMapper.findByUserId(userId);
        if (account == null) {
            throw new AccountNotBindException(userId);
        }
        if (account.getAlipayUsername().equals(payDTO.getAlipayUsername())
                && passwordEncoder.matches(payDTO.getPaymentPassword(), account.getPaymentPassword())) {
            Double price = order.getPrice();
            if (price.compareTo(account.getBalance()) > 0) {
                log.info("{} 用户余额不足", userId);
                throw new BalanceNotEnoughException(String.valueOf(account.getBalance()));
            }
            // 1) 扣除余额
            account.setBalance(account.getBalance() - price);
            alipayDOMapper.updateByPrimaryKeySelective(account);
            // 2) 调整订单状态
            orderService.finishOrder(order);
            // 3) 增加用户消费金额
            studentService.incrementConsumptions(userId, price);
            // 4) 添加选课记录
            studyService.save(
                    StudyRecordDO.builder()
                            .classDO(order.getClassDO())
                            .student(order.getStudent())
                            .status(StudyStatus.STUDYING)
                            .order(order)
                            .normalScore(0d)
                            .totalScore(0d)
                            .build()
            );
            order.getClassDO().setCourse(courseService.findCourseById(order.getClassDO().getCourseId()));
            return order;
        } else {
            log.info("{} 用户支付账号、密码不匹配", userId);
            throw new AccountUsernameOrPasswordInCorrectException(userId);
        }
    }


    @Transactional
    @Override
    public void refund(OrderDO orderDO) {
        // 根据开课时间 返还部分金额
        // 订单状态为已取消
        // 学生的消费金额减少，VIP等级调整
        // 选课表中状态设置为已退选
        Long userId = orderDO.getStudent().getId();
        CourseDO course = courseService.findCourseAndClassesById(orderDO.getClassDO().getCourseId());
        if (LocalDateTime.now().isBefore(course.getStartTime())) {
            // 开课前退款，可以退部分金额
            // 1) 退部分金额
            AlipayDO account = alipayDOMapper.findByUserId(userId);
            account.setBalance(account.getBalance() + orderDO.getPrice() * RefundProperties.BEFORE_START_RATIO);
            alipayDOMapper.updateByPrimaryKeySelective(account);
        }
        // 2) 调整订单状态，并且调整课程状态，已选人数-1
        orderService.cancelOrder(orderDO);

        // 3) 减少消费金额
        studentService.incrementConsumptions(userId, -orderDO.getPrice());

        // 4) 调整选课记录状态
        StudyRecordDO studyRecordDO = studyService.findByOrderId(orderDO.getId());
        studyRecordDO.setStatus(StudyStatus.QUIT);
        studyService.update(studyRecordDO);
    }

    @Transactional(readOnly = true)
    @Override
    public AccountResult isBound(Long userId) {
        AlipayDO alipayDO = alipayDOMapper.findByUserId(userId);
        if (alipayDO == null) {
            return new AccountResult(false, null);
        } else {
            return new AccountResult(true, alipayDO.getBalance());
        }
    }

    @Transactional
    @Override
    public void incrementManagerValue(Double value) {
        ManagerDO manager = managerService.findManager();
        AlipayDO alipayDO = alipayDOMapper.findByUserId(manager.getId());
        alipayDO.setBalance(alipayDO.getBalance() + value);
        alipayDOMapper.updateByPrimaryKeySelective(alipayDO);
    }

    @Transactional
    @Override
    public void incrementInstitutionValue(Long institutionId, Double value) {
        AlipayDO alipayDO = alipayDOMapper.findByUserId(institutionId);
        if (alipayDO == null) {
            throw new AccountNotBindException(institutionId);
        }
        alipayDO.setBalance(alipayDO.getBalance() + value);
        alipayDOMapper.updateByPrimaryKeySelective(alipayDO);
    }
}
