package com.sinjinsong.icourse.core.service.course;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.entity.course.StudyRecordDO;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
public interface StudyService {
    void save(StudyRecordDO studyRecordDO);

    void update(StudyRecordDO studyRecordDO);

    PageInfo<StudyRecordDO> findAllByStudentId(Long studentId, int pageNum, int pageSize);

    PageInfo<StudyRecordDO> findAllByClassId(Long classId, int pageNum, int pageSize);

    StudyRecordDO findById(Long id);

    StudyRecordDO findByOrderId(Long orderId);
}
