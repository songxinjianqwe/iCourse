package com.sinjinsong.icourse.core.exception.order;


import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/10/7.
 */

@RestResponseStatus(value = HttpStatus.NOT_FOUND,code = 12)
@RestField("order")
public class OrderNotFoundException extends BaseRestException {
    public OrderNotFoundException(String order){
        super(order);
    }
}
