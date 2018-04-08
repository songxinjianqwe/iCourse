package com.sinjinsong.icourse.core.exception.pay;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestField("userId")
@RestResponseStatus(value = HttpStatus.BAD_REQUEST, code = 40013)
public class AccountNotBindException extends BaseRestException {
    public AccountNotBindException(Long userId) {
        super(userId);
    }
}
