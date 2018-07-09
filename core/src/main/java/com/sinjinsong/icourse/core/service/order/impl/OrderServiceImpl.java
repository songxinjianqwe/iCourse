package com.sinjinsong.icourse.core.service.order.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.order.OrderDOMapper;
import com.sinjinsong.icourse.core.domain.dto.order.OrderQueryConditionDTO;
import com.sinjinsong.icourse.core.domain.entity.course.StudyRecordDO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.enumeration.course.StudyStatus;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.core.enumeration.order.PayType;
import com.sinjinsong.icourse.core.exception.course.ClassFullException;
import com.sinjinsong.icourse.core.exception.course.CourseSelectDuplicationException;
import com.sinjinsong.icourse.core.properties.OrderProperties;
import com.sinjinsong.icourse.core.service.course.CourseService;
import com.sinjinsong.icourse.core.service.course.StudyService;
import com.sinjinsong.icourse.core.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudyService studyService;

    private void populateCourse(PageInfo<OrderDO> orders) {
        orders.getList().forEach(order -> {
            order.getClassDO().setCourse(courseService.findCourseById(order.getClassDO().getCourseId()));
        });
    }

    @Transactional
    @Override
    public OrderDO placeOrder(OrderDO order) {
        Long classId = order.getClassDO().getId();
        List<OrderDO> queryResult = orderDOMapper.findByStudentIdAndClassId(order.getStudent().getId(), classId);
        if (queryResult.size() != 0 ){
            if(queryResult.stream().anyMatch( o ->
                 o.getStatus() == OrderStatus.UNPAID || o.getStatus() == OrderStatus.PAID)
            ){
                throw new CourseSelectDuplicationException(classId);
            }
        }
        order.setInstitution(courseService.findInstitutionByClassId(classId));
        if (courseService.chooseClass(classId)) {
            // 将所选班级的已选人数+1
            order.setPlaceTime(LocalDateTime.now());
            order.setIsSettled(Boolean.FALSE);

            if (order.getPayType() == PayType.ONLINE) {
                order.setStatus(OrderStatus.UNPAID);
                orderDOMapper.insert(order);
            } else if (order.getPayType() == PayType.OFFLINE) {
                order.setStatus(OrderStatus.PAID);
                orderDOMapper.insert(order);
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
            }
            return orderDOMapper.selectByPrimaryKey(order.getId());
        } else {
            throw new ClassFullException(classId);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findAll(Integer pageNum, Integer pageSize) {
        PageInfo<OrderDO> orders = orderDOMapper.findAll(pageNum, pageSize).toPageInfo();
        populateCourse(orders);
        return orders;
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findAllByCondition(OrderQueryConditionDTO queryDTO, Integer pageNum, Integer pageSize) {
        PageInfo<OrderDO> orders = orderDOMapper.findByCondition(queryDTO, pageNum, pageSize).toPageInfo();
        populateCourse(orders);
        return orders;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDO findById(Long orderId) {
        return orderDOMapper.selectByPrimaryKey(orderId);
    }

    @Transactional
    @Override
    public void updateOrder(OrderDO order) {
        orderDOMapper.updateByPrimaryKeySelective(order);
    }

    @Transactional
    @Override
    public void updateTimeOutOrders() {
        findAllByCondition(OrderQueryConditionDTO.builder().status(OrderStatus.UNPAID).build(), 0, 0).getList().forEach(
                (OrderDO order) -> {
                    log.info("未付款订单号:{}", order.getId());
                    if (Duration.between(order.getPlaceTime(), LocalDateTime.now()).toMinutes() >= OrderProperties.TIME_OUT_SPAN) {
                        order.setStatus(OrderStatus.TIME_OUT);
                        orderDOMapper.updateByPrimaryKeySelective(order);
                        courseService.unChooseClass(order.getClassDO().getId());
                        log.info("超时订单:{}", order.getId());
                    }
                }
        );
    }

    @Transactional
    @Override
    public void finishOrder(OrderDO order) {
        order.setStatus(OrderStatus.PAID);
        orderDOMapper.updateByPrimaryKeySelective(order);
    }

    @Transactional
    @Override
    public void cancelOrder(OrderDO order) {
        order.setStatus(OrderStatus.CANCELED);
        orderDOMapper.updateByPrimaryKeySelective(order);
        courseService.unChooseClass(order.getClassDO().getId());
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findUnSettledOrders(Integer pageNum, Integer pageSize) {
        PageInfo<OrderDO> orders = orderDOMapper.findUnSettledOrders(pageNum, pageSize).toPageInfo();
        populateCourse(orders);
        return orders;
    }

    @Transactional
    @Override
    public void setOrdersSettledBatch(List<Long> orderIds) {
        orderDOMapper.setOrdersSettledBatch(orderIds);
    }
}
