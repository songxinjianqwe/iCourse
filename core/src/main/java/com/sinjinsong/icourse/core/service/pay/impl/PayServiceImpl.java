package com.sinjinsong.icourse.core.service.pay.impl;

import com.sinjinsong.icourse.core.dao.pay.AlipayDOMapper;
import com.sinjinsong.icourse.core.domain.dto.pay.PayDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.pay.AlipayDO;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.core.exception.order.OrderNotFoundException;
import com.sinjinsong.icourse.core.exception.pay.BalanceNotEnoughException;
import com.sinjinsong.icourse.core.exception.pay.PaymentPasswordInCorrectException;
import com.sinjinsong.icourse.core.service.order.OrderService;
import com.sinjinsong.icourse.core.service.pay.PayService;
import com.sinjinsong.icourse.core.service.student.StudentService;
import com.sinjinsong.icourse.core.service.vip.VipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private AlipayDOMapper alipayDOMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private VipService vipService;

    @Transactional
    @Override
    public void bind(AlipayDO aliPayDO) {
        aliPayDO.setBalance(0d);
        aliPayDO.setPaymentPassword(passwordEncoder.encode(aliPayDO.getPaymentPassword()));
        alipayDOMapper.insert(aliPayDO);
    }

    @Transactional
    @Override
    public void deposit(Long userId, PayDTO payDTO, int amount) {
        AlipayDO realData = alipayDOMapper.selectByPrimaryKey(userId);
        if (realData.getAlipayUsername().equals(payDTO.getAlipayUsername())
                && passwordEncoder.matches(payDTO.getPaymentPassword(), realData.getPaymentPassword())) {
            realData.setBalance(realData.getBalance() + amount);
            alipayDOMapper.updateByPrimaryKeySelective(realData);
        } else {
            log.info("{} 用户支付账号、密码不匹配", userId);
            throw new PaymentPasswordInCorrectException(userId);
        }
    }

    @Transactional
    @Override
    public void pay(Long orderId, PayDTO payDTO) {
        // 如果支付账号、密码匹配，并且余额足够支持，那么
        // 扣除用户余额
        // 订单状态设置为已支付
        // 学生的消费金额增加，VIP等级调整
        OrderDO order = orderService.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(String.valueOf(orderId));
        }
        Long userId = order.getStudent().getId();
        AlipayDO account = alipayDOMapper.selectByPrimaryKey(userId);

        if (account.getAlipayUsername().equals(payDTO.getAlipayUsername())
                && passwordEncoder.matches(payDTO.getPaymentPassword(), account.getPaymentPassword())) {
            double discount = vipService.findDiscountByVipLevel(order.getStudent().getVipLevel());
            Double discountedPrice = order.getPrice() * discount ;
            if (discountedPrice.compareTo(account.getBalance()) > 0) {
                log.info("{} 用户余额不足", userId);
                throw new BalanceNotEnoughException(String.valueOf(account.getBalance()));
            }
            // 
            account.setBalance(account.getBalance() - discountedPrice);
            alipayDOMapper.updateByPrimaryKeySelective(account);
            // 
            order.setStatus(OrderStatus.PAID);
            order.setDiscount(discount);
            orderService.updateOrder(order);
            // 
            studentService.incrementConsumptions(userId, discountedPrice);
        } else {
            log.info("{} 用户支付账号、密码不匹配", userId);
            throw new PaymentPasswordInCorrectException(userId);
        }
    }
}
