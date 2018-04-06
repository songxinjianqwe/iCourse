package com.sinjinsong.icourse.core.controller.study;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.properties.PageProperties;
import com.sinjinsong.icourse.core.domain.entity.course.StudyRecordDO;
import com.sinjinsong.icourse.core.exception.study.StudyRecordNotFoundException;
import com.sinjinsong.icourse.core.service.course.StudyService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestController
@RequestMapping("/study")
@Slf4j
public class StudyController {
    @Autowired
    private StudyService studyService;
    
    @PreAuthorize("hasRole('INSTITUTION')")
    @PutMapping
    public StudyRecordDO update(@RequestBody @Valid StudyRecordDO studyRecordDO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        log.info("{}", studyRecordDO);
        studyService.update(studyRecordDO);
        return studyRecordDO;
    }
    
    @GetMapping("/students/{studentId}")
    public PageInfo<StudyRecordDO> findAllByStudentId(@PathVariable("studentId") Long studentId, @RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return studyService.findAllByStudentId(studentId, pageNum, pageSize);
    }

    @GetMapping("/classes/{classId}")
    public PageInfo<StudyRecordDO> findAllByClassId(@PathVariable("classId") Long classId, @RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return studyService.findAllByClassId(classId, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public StudyRecordDO findById(@PathVariable("id") Long id) {
        StudyRecordDO studyRecordDO = studyService.findById(id);
        if(studyRecordDO == null) {
            throw new StudyRecordNotFoundException(String.valueOf(id));
        }
        return studyRecordDO;
    }
    
    @GetMapping("/orders/{orderId}")
    public StudyRecordDO findByOrderId(@PathVariable("orderId") Long orderId) {
        StudyRecordDO studyRecordDO = studyService.findByOrderId(orderId);
        if(studyRecordDO == null) {
            throw new StudyRecordNotFoundException("order-" + orderId);
        }
        return studyRecordDO;
    }
}
