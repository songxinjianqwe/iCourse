package com.sinjinsong.icourse.core.controller.institution;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.properties.PageProperties;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;
import com.sinjinsong.icourse.core.enumeration.institution.InstitutionStatus;
import com.sinjinsong.icourse.core.exception.user.PasswordNotFoundException;
import com.sinjinsong.icourse.core.exception.user.QueryUserModeNotFoundException;
import com.sinjinsong.icourse.core.exception.user.UserNotFoundException;
import com.sinjinsong.icourse.core.service.institution.InstitutionService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestController
@RequestMapping("/institutions")
@Slf4j
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InstitutionDO register(@RequestBody @Valid InstitutionDO institutionDO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        } else if (institutionDO.getPassword() == null) {
            throw new PasswordNotFoundException();
        }
        institutionService.register(institutionDO);
        return institutionDO;
    }

    @PutMapping
    public InstitutionDO update(@RequestBody @Valid InstitutionDO institutionDO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        institutionService.update(institutionDO);
        return institutionDO;
    }

    @GetMapping
    public PageInfo<InstitutionDO> findAll(@RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return institutionService.findAll(pageNum, pageSize);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/status")
    public PageInfo<InstitutionDO> findAllByStatus(@RequestParam("status") InstitutionStatus status, @RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return institutionService.findAllByStatus(status, pageNum, pageSize);
    }
    
    @GetMapping("/query")
    public InstitutionDO findByKey(@RequestParam("key") String key, @RequestParam("mode") String mode) {
        InstitutionDO result = null;
        if (mode.equalsIgnoreCase("id")) {
            result = institutionService.findById(Long.valueOf(key));
        } else if (mode.equalsIgnoreCase("username")) {
            result = institutionService.findByUsername(key);
        } else {
            throw new QueryUserModeNotFoundException(mode);
        }
        if (result == null) {
            throw new UserNotFoundException(key);
        }
        return result;
    }

    @PutMapping("/approval")
    @PreAuthorize("hasRole('MANAGER')")
    public void approveInstitutionBatch(@RequestBody List<Long> ids) {
        institutionService.approveBatch(ids);
    }
}
