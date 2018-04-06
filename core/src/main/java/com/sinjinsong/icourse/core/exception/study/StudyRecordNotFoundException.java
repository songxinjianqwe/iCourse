package com.sinjinsong.icourse.core.exception.study;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestField("id")
@RestResponseStatus(value = HttpStatus.NOT_FOUND,code = 40415)
public class StudyRecordNotFoundException extends BaseRestException  {
    public StudyRecordNotFoundException(String id) {
        super(id);
    }
}
