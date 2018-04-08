package com.sinjinsong.icourse.core.controller.course;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.properties.PageProperties;
import com.sinjinsong.icourse.common.security.domain.JwtUser;
import com.sinjinsong.icourse.core.domain.dto.course.CourseDTO;
import com.sinjinsong.icourse.core.domain.dto.course.CourseDTOForCascadeSelector;
import com.sinjinsong.icourse.core.domain.entity.course.CourseDO;
import com.sinjinsong.icourse.core.service.course.CourseService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasRole('INSTITUTION')")
    public CourseDO addCourse(@RequestBody @Valid CourseDTO courseDTO, BindingResult bindingResult,@AuthenticationPrincipal JwtUser institution){
        if(bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        courseDTO.setInstitutionId(institution.getId());
        log.info("{}",courseDTO);
        return courseService.addCourse(courseDTO);
    }
    
    @GetMapping
    public PageInfo<CourseDO> findAll(@RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return courseService.findAll(pageNum,pageSize);
    }
    
    
    @GetMapping("/institutions/{institutionId}")
    @PreAuthorize("hasRole('INSTITUTION')")
    public PageInfo<CourseDO> findByInstitutionId(@PathVariable("institutionId") Long institutionId, @RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize){
        return courseService.findCoursesByInstitutionId(institutionId,pageNum,pageSize);
    }
    
    @GetMapping("/{id}")
    public CourseDO findById(@PathVariable("id") Long id){
        return courseService.findCourseAndClassesById(id);
    }
    
    @GetMapping("/institutions/{institutionId}/cascade_selector")
    public List<CourseDTOForCascadeSelector> findAllByInstitutionId(@PathVariable("institutionId") Long institutionId) {
        return courseService.findAllByInstitutionId(institutionId);
    }
}
