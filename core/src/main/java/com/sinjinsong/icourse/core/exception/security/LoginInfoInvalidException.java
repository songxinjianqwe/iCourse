package com.sinjinsong.icourse.core.exception.security;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import com.sinjinsong.icourse.core.domain.dto.user.LoginDTO;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@RestResponseStatus(value = HttpStatus.UNAUTHORIZED, code = 1)
@RestField("loginInfo")
public class LoginInfoInvalidException extends BaseRestException {
    public LoginInfoInvalidException(LoginDTO loginDTO) {
        super(loginDTO);
    }
}
