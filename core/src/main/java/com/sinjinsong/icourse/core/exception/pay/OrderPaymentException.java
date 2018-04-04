package com.sinjinsong.icourse.core.exception.pay;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author sinjinsong
 * @date 2017/12/27
 */
@RestField("order")
@RestResponseStatus(value=HttpStatus.BAD_REQUEST,code=40410)
public class OrderPaymentException extends BaseRestException {
    public OrderPaymentException(Long orderId){
        super(orderId);
    }
}
