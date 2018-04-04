package com.sinjinsong.icourse.core.exception.security;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/4/28.
 */
@RestResponseStatus(value = HttpStatus.UNAUTHORIZED,code = 3)
@RestField("userStatus")
public class UserStatusInvalidException extends BaseRestException {
    public UserStatusInvalidException(String userStatus){
        super(userStatus);
    }
}
