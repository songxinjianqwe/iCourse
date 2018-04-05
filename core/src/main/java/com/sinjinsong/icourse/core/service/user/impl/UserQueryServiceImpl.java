package com.sinjinsong.icourse.core.service.user.impl;

import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;
import com.sinjinsong.icourse.core.domain.entity.manager.ManagerDO;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.domain.dto.user.AbstractUserDTO;
import com.sinjinsong.icourse.core.enumeration.institution.InstitutionStatus;
import com.sinjinsong.icourse.core.enumeration.student.StudentStatus;
import com.sinjinsong.icourse.core.service.institution.InstitutionService;
import com.sinjinsong.icourse.core.service.manager.ManagerService;
import com.sinjinsong.icourse.core.service.student.StudentService;
import com.sinjinsong.icourse.core.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstitutionService institutionService;
    
    @Transactional(readOnly = true)
    @Override
    public AbstractUserDTO findByUsername(String username) {
        StudentDO student = studentService.findByUsername(username);
        if (student != null) {
            return AbstractUserDTO.builder()
                    .id(student.getId())
                    .username(username)
                    .password(student.getPassword())
                    .isValid(student.getStatus() == StudentStatus.ACTIVATED)
                    .roles(student.getRoles())
                    .build();
        }

        InstitutionDO institution = institutionService.findByUsername(username);
        if(institution != null) {
            return AbstractUserDTO.builder()
                    .id(institution.getId())
                    .username(username)
                    .password(institution.getPassword())
                    .isValid(institution.getStatus() == InstitutionStatus.APPROVED)
                    .roles(institution.getRoles())
                    .build();
        }
        
        ManagerDO manager = managerService.findByUsername(username);
        if(manager != null) {
            return AbstractUserDTO.builder()
                    .id(manager.getId())
                    .username(username)
                    .password(manager.getPassword())
                    .isValid(true)
                    .roles(manager.getRoles())
                    .build();
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(String username) {
        StudentDO student = studentService.findByUsername(username);
        if (student != null) {
            return true;
        }

        InstitutionDO institution = institutionService.findByUsername(username);
        if(institution != null) {
            return true;
        }
        
        ManagerDO manager = managerService.findByUsername(username);
        if(manager != null) {
            return true;
        }
        return false;
    }

}
