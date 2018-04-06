package com.sinjinsong.icourse.common.exception.security;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/9.
 */
@RestResponseStatus(value = HttpStatus.FORBIDDEN,code=1)
@RestField("role")
public class RestAccessDeniedException extends BaseRestException {
    public RestAccessDeniedException(String role){
        super(role);
    }
}
