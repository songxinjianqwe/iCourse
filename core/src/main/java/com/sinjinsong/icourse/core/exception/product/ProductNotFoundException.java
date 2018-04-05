package com.sinjinsong.icourse.core.exception.product;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/6.
 */
@RestResponseStatus(value = HttpStatus.NOT_FOUND,code=10)
@RestField("course")
public class ProductNotFoundException extends BaseRestException {
    public ProductNotFoundException(String key){
        super(key);
    }
}
