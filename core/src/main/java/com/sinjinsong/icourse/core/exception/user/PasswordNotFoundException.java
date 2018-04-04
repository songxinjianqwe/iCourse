package com.sinjinsong.icourse.core.exception.user;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/10/15.
 */
@RestResponseStatus(value= HttpStatus.NOT_FOUND,code=12)
@RestField("password")
public class PasswordNotFoundException extends BaseRestException {
    public PasswordNotFoundException(){
        super(null);
    }
}
