package com.sinjinsong.icourse.core.service.student;


import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.dto.student.StudentDiscountDTO;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;

import java.util.List;

/**
 * Created by SinjinSong on 2017/4/27.
 */
public interface StudentService {
    StudentDO findByUsername(String username);
    StudentDO findById(Long id);
    void save(StudentDO userDO);
    void update(StudentDO userDO);
    PageInfo<StudentDO> findAll(int pageNum, int pageSize);
    StudentDO findByEmail(String email);
    void incrementConsumptions(Long studentId,double value);
    List<StudentDiscountDTO> findIdAndUsernameAndDiscountByUsernameContaining(String username);
}
