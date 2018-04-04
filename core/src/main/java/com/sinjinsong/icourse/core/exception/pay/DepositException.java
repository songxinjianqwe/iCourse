package com.sinjinsong.icourse.core.exception.pay;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/10/7.
 */
@RestResponseStatus(value = HttpStatus.BAD_REQUEST,code = 10)
public class DepositException extends BaseRestException {
    public DepositException(String amount){
        super(amount);
    }
}
