package com.sinjinsong.icourse.core.service.course;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.dto.course.CourseDTO;
import com.sinjinsong.icourse.core.domain.entity.course.ClassDO;
import com.sinjinsong.icourse.core.domain.entity.course.CourseDO;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
public interface CourseService {
    CourseDO addCourse(CourseDTO courseDTO);
    
    PageInfo<CourseDO> findCoursesByInstitutionId(Long institutionId, int pageNum, int pageSize);

    CourseDO findCourseById(Long courseId);

    boolean chooseClass(Long classId);
    ClassDO findClassById(Long classId);
    InstitutionDO findInstitutionByClassId(Long classId);
}
