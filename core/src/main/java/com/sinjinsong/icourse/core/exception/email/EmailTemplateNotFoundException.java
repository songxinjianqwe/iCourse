package com.sinjinsong.icourse.core.exception.email;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author songx
 * @date 2017/12/19
 */
@RestResponseStatus(value = HttpStatus.NOT_FOUND, code = 1)
@RestField("email")
public class EmailTemplateNotFoundException extends BaseRestException {
    public EmailTemplateNotFoundException(String email) {
        super(email);
    }
}
