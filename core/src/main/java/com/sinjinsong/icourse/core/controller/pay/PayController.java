package com.sinjinsong.icourse.core.controller.pay;

import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.exception.security.AccessDeniedException;
import com.sinjinsong.icourse.common.security.domain.JWTUser;
import com.sinjinsong.icourse.core.domain.dto.pay.PaymentPasswordDTO;
import com.sinjinsong.icourse.core.domain.dto.pay.PaymentPasswordModificationDTO;
import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.exception.order.OrderNotFoundException;
import com.sinjinsong.icourse.core.exception.pay.DepositException;
import com.sinjinsong.icourse.core.service.order.OrderService;
import com.sinjinsong.icourse.core.service.pay.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by SinjinSong on 2017/10/7.
 */
@RestController
@Api(value = "commit", description = "支付相关")
@Slf4j
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value = "/users/{userId}/deposit", method = RequestMethod.POST)
    @ApiOperation(value = "充值", authorizations = {@Authorization("登录")})
    public void deposit(@PathVariable("userId") @ApiParam(value = "用户id", required = true) Long userId, @RequestParam("amount") @ApiParam(value = "充值金额", required = true) Integer amount) {
        if (amount <= 0) {
            throw new DepositException(String.valueOf(amount));
        }
        payService.deposit(userId, amount);
    }

    /**
     * @param orderId
     * @param paymentPassword
     * @param user
     */
    @RequestMapping(value = "/pay/{orderId}", method = RequestMethod.POST)
    @ApiOperation(value = "订单付款", authorizations = {@Authorization("登录")})
    public void pay(@PathVariable("orderId") @ApiParam(value = "订单id", required = true) Long orderId, @RequestBody PaymentPasswordDTO paymentPassword, @AuthenticationPrincipal JWTUser user) {
        OrderDO order = orderService.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(String.valueOf(orderId));
        }
        if (!user.getId().equals(order.getUser().getId())) {
            throw new AccessDeniedException(user.getUsername());
        }
        
//        accountService.commit(order, paymentPassword.getPaymentPassword());
    }

    @RequestMapping(value = "/users/{userId}/payment_password", method = RequestMethod.POST)
    @ApiOperation(value = "设置支付密码", authorizations = {@Authorization("登录")})
    public void setPaymentPassword(@PathVariable("userId") @ApiParam(value = "订单id", required = true) Long userId, @RequestBody @Valid PaymentPasswordModificationDTO dto, BindingResult result) {
        if(result.hasErrors()){
            throw new RestValidationException(result.getFieldErrors());
        }
        payService.setPaymentPassword(userId, dto.getOldPaymentPassword(), dto.getNewPaymentPassword());
    }
}
