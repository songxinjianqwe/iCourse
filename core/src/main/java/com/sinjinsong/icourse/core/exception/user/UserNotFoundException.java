package com.sinjinsong.icourse.core.exception.user;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/6.
 */
@RestResponseStatus(value = HttpStatus.NOT_FOUND,code=8)
@RestField("queryKey")
public class UserNotFoundException extends BaseRestException {
    public UserNotFoundException(String key){
        super(key);
    }
}
