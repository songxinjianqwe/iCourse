package com.sinjinsong.icourse.core.service.course.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.course.ClassDOMapper;
import com.sinjinsong.icourse.core.dao.course.CourseDOMapper;
import com.sinjinsong.icourse.core.domain.dto.course.ClassDTO;
import com.sinjinsong.icourse.core.domain.dto.course.CourseDTO;
import com.sinjinsong.icourse.core.domain.dto.course.CourseDTOForCascadeSelector;
import com.sinjinsong.icourse.core.domain.entity.course.ClassDO;
import com.sinjinsong.icourse.core.domain.entity.course.CourseDO;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;
import com.sinjinsong.icourse.core.service.course.CourseService;
import com.sinjinsong.icourse.core.service.institution.InstitutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDOMapper courseDOMapper;
    @Autowired
    private ClassDOMapper classDOMapper;
    @Autowired
    private InstitutionService institutionService;
    
    @Transactional
    @Override
    public CourseDO addCourse(CourseDTO courseDTO) {
        CourseDO course = CourseDO.builder()
                .institution(InstitutionDO.builder().id(courseDTO.getInstitutionId()).build())
                .name(courseDTO.getName())
                .type(courseDTO.getType())
                .description(courseDTO.getDescription())
                .placeTime(LocalDateTime.now())
                .startTime(courseDTO.getStartTime())
                .build();
        courseDOMapper.insert(course);
        for (ClassDTO classDTO : courseDTO.getClasses()) {
            classDOMapper.insert(
                    ClassDO.builder()
                            .name(classDTO.getName())
                            .courseId(course.getId())
                            .price(classDTO.getPrice())
                            .threshold(classDTO.getThreshold())
                            .currentCount(0)
                            .build()
            );
        }
        return courseDOMapper.selectByPrimaryKey(course.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<CourseDO> findCoursesByInstitutionId(Long institutionId, int pageNum, int pageSize) {
        return courseDOMapper.findAllByInstitutionId(institutionId, pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @Override
    public CourseDO findCourseAndClassesById(Long courseId) {
        return courseDOMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public CourseDO findCourseById(Long courseId) {
        return courseDOMapper.findSimpleCourseById(courseId);
    }

    @Transactional
    @Override
    public boolean chooseClass(Long classId) {
        return classDOMapper.incrementCurrentCountConsistently(classId) > 0;
    }

    @Override
    public void unChooseClass(Long classId) {
        classDOMapper.decrementCurrentCount(classId);
    }

    @Transactional(readOnly = true)
    @Override
    public ClassDO findClassById(Long classId) {
        return classDOMapper.selectByPrimaryKey(classId);
    }

    @Override
    public InstitutionDO findInstitutionByClassId(Long classId) {
        return institutionService.findInstitutionByClassId(classId);
    }

    @Override
    public PageInfo<CourseDO> findAll(int pageNum, int pageSize) {
        return courseDOMapper.findAllByPage(pageNum,pageSize).toPageInfo();
    }
    
    @Override
    public List<CourseDTOForCascadeSelector> findAllByInstitutionId(Long institutionId) {
        List<CourseDTOForCascadeSelector> result = new ArrayList<>();
        courseDOMapper.findAll(institutionId).forEach(course -> {
           List<CourseDTOForCascadeSelector.ClassDTOForCascadeSelector> classes = new ArrayList<>();
           course.getClasses().forEach(classDO -> {
               classes.add(new CourseDTOForCascadeSelector.ClassDTOForCascadeSelector(classDO.getName(), classDO.getId(),classDO.getPrice()));
           });
           result.add(new CourseDTOForCascadeSelector(course.getName(),course.getId(),classes));
        });
        return result;
    }
}
