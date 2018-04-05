package com.sinjinsong.icourse.core.service.order;


import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.dto.order.OrderQueryConditionDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;

/**
 * Created by SinjinSong on 2017/10/6.
 */
public interface OrderService {
    OrderDO placeOrder(OrderDO order);

    PageInfo<OrderDO> findAll(Integer pageNum, Integer pageSize);

    PageInfo<OrderDO> findAllByCondition(OrderQueryConditionDTO queryDTO, Integer pageNum, Integer pageSize);

    OrderDO findById(Long orderId);

    void updateOrder(OrderDO order);

    void updateTimeOutOrders();

}
