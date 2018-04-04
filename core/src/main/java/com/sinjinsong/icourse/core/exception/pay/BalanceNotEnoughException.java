package com.sinjinsong.icourse.core.exception.pay;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/10/7.
 */
@RestResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,code = 1)
@RestField("balance")
public class BalanceNotEnoughException extends BaseRestException {
    public BalanceNotEnoughException(String balance){
        super(balance);
    }
}
