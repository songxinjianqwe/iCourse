package com.sinjinsong.icourse.common.exception.security;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/7.
 */
@RestResponseStatus(value= HttpStatus.UNAUTHORIZED,code=4)
@RestField("tokenStatus")
public class TokenStateInvalidException extends BaseRestException {
    public TokenStateInvalidException(String tokenStatus){
        super(tokenStatus);
    }
}
