package com.sinjinsong.icourse.core.service.order.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.order.OrderDOMapper;
import com.sinjinsong.icourse.core.domain.dto.order.OrderQueryConditionDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.core.properties.OrderProperties;
import com.sinjinsong.icourse.core.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDOMapper mapper;

    @Override
    public OrderDO placeOrder(OrderDO order) {
        mapper.insert(order);
        return order;
    }

    @Override
    public PageInfo<OrderDO> findAll(Integer pageNum, Integer pageSize) {
        return mapper.findAll(pageNum, pageSize).toPageInfo();
    }

    @Override
    public PageInfo<OrderDO> findAllByCondition(OrderQueryConditionDTO queryDTO, Integer pageNum, Integer pageSize) {
        return mapper.findByCondition(queryDTO, pageNum, pageSize).toPageInfo();
    }

    @Override
    public OrderDO findById(Long orderId) {
        return mapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void updateOrder(OrderDO order) {
        mapper.updateByPrimaryKeySelective(order);
    }
    
    @Override
    public void updateTimeOutOrders() {
        findAllByCondition(OrderQueryConditionDTO.builder().status(OrderStatus.UNPAID).build(), 0, 0).getList().forEach(
                (OrderDO order) -> {
                    log.info("未付款订单号:{}", order.getId());
                    if (Duration.between(order.getPlaceTime(), LocalDateTime.now()).toMinutes() >= OrderProperties.TIME_OUT_SPAN) {
                        order.setStatus(OrderStatus.TIME_OUT);
                        mapper.updateByPrimaryKeySelective(order);
                        log.info("超时订单:{}", order.getId());
                    }
                }
        );
    }
}
