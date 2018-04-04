package com.sinjinsong.icourse.core.exception.user;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@RestResponseStatus(value = HttpStatus.NOT_FOUND,code =1)
@RestField("queryMode")
public class QueryUserModeNotFoundException extends BaseRestException {
    public QueryUserModeNotFoundException(String mode){
        super(mode);
    }
}
