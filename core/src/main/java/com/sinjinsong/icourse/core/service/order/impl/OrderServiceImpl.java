package com.sinjinsong.icourse.core.service.order.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.order.OrderDOMapper;
import com.sinjinsong.icourse.core.domain.dto.order.OrderQueryConditionDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.core.exception.course.ClassFullException;
import com.sinjinsong.icourse.core.exception.course.CourseSelectDuplicationException;
import com.sinjinsong.icourse.core.properties.OrderProperties;
import com.sinjinsong.icourse.core.service.course.CourseService;
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

    @Transactional
    @Override
    public OrderDO placeOrder(OrderDO order) {
        Long classId = order.getClassDO().getId();
        if(orderDOMapper.findByStudentIdAndClassId(order.getStudent().getId(),classId) != null) {
            throw new CourseSelectDuplicationException(classId);
        }
        order.setInstitution(courseService.findInstitutionByClassId(classId));
        if (courseService.chooseClass(classId)) {
            // 将所选班级的已选人数+1
            order.setPlaceTime(LocalDateTime.now());
            order.setStatus(OrderStatus.UNPAID);
            orderDOMapper.insert(order);
            return orderDOMapper.selectByPrimaryKey(order.getId());
        } else {
            throw new ClassFullException(classId);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findAll(Integer pageNum, Integer pageSize) {
        return orderDOMapper.findAll(pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findAllByCondition(OrderQueryConditionDTO queryDTO, Integer pageNum, Integer pageSize) {
        return orderDOMapper.findByCondition(queryDTO, pageNum, pageSize).toPageInfo();
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
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<OrderDO> findUnSettledOrders(Integer pageNum, Integer pageSize) {
        return orderDOMapper.findUnSettledOrders(pageNum,pageSize).toPageInfo();
    }

    @Transactional
    @Override
    public void setOrdersSettledBatch(List<Long> orderIds) {
        orderDOMapper.setOrdersSettledBatch(orderIds);
    }


}
