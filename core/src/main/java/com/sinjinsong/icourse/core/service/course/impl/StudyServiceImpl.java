package com.sinjinsong.icourse.core.service.course.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.course.StudyRecordDOMapper;
import com.sinjinsong.icourse.core.domain.entity.course.StudyRecordDO;
import com.sinjinsong.icourse.core.service.course.CourseService;
import com.sinjinsong.icourse.core.service.course.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@Service
@Slf4j
public class StudyServiceImpl implements StudyService {
    @Autowired
    private StudyRecordDOMapper studyRecordDOMapper;
    @Autowired
    private CourseService courseService;


    private void populateCourse(PageInfo<StudyRecordDO> studyRecords) {
        studyRecords.getList().forEach(studyRecordDO -> {
            studyRecordDO.getClassDO().setCourse(courseService.findCourseById(studyRecordDO.getClassDO().getCourseId()));
        });
    }


    @Transactional
    @Override
    public void save(StudyRecordDO studyRecordDO) {
        studyRecordDOMapper.insert(studyRecordDO);
    }

    @Transactional
    @Override
    public void update(StudyRecordDO studyRecordDO) {
        studyRecordDOMapper.updateByPrimaryKeySelective(studyRecordDO);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<StudyRecordDO> findAllByStudentId(Long studentId, int pageNum, int pageSize) {
        PageInfo<StudyRecordDO> studyRecords = studyRecordDOMapper.findAllByStudentId(studentId, pageNum, pageSize).toPageInfo();
        populateCourse(studyRecords);
        return studyRecords;
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<StudyRecordDO> findAllByClassId(Long classId, int pageNum, int pageSize) {
        PageInfo<StudyRecordDO> studyRecords = studyRecordDOMapper.findAllByClassId(classId, pageNum, pageSize).toPageInfo();
        populateCourse(studyRecords);
        return studyRecords;
    }

    @Transactional(readOnly = true)
    @Override
    public StudyRecordDO findById(Long id) {
        return studyRecordDOMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public StudyRecordDO findByOrderId(Long orderId) {
        return studyRecordDOMapper.findByOrderId(orderId);
    }
}
