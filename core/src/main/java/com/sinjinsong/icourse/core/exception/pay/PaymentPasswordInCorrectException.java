package com.sinjinsong.icourse.core.exception.pay;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/10/14.
 */
@RestResponseStatus(value= HttpStatus.FORBIDDEN,code=2)
@RestField("userId")
public class PaymentPasswordInCorrectException extends BaseRestException {
    public PaymentPasswordInCorrectException(Long userId){
        super(userId);
    }
}
