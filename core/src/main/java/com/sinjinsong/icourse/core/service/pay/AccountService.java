package com.sinjinsong.icourse.core.service.pay;

import com.sinjinsong.icourse.core.domain.dto.pay.PayDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface AccountService {
    /**
     * 将用户账号绑定到一个已有的支付宝账号
     * 如果支付宝的用户名和密码匹配，并且没有绑定过用户账号，则可以绑定
     * @param userId
     * @param payDTO
     */
    void bind(Long userId,PayDTO payDTO);

    /**
     * 用该账号去支付一个订单
     * @param orderId
     * @param payDTO
     */
    void pay(Long orderId, PayDTO payDTO);

    /**
     * 退款至支付该订单的支付宝账号
     * @param orderDO
     */
    void refund(OrderDO orderDO);
}
