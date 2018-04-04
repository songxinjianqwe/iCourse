package com.sinjinsong.icourse.core.exception.user;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

@RestResponseStatus(value = HttpStatus.CONFLICT, code = 1)
@RestField("name")
public class UsernameExistedException extends BaseRestException {
    public UsernameExistedException(String name) {
        super(name);
    }

}
