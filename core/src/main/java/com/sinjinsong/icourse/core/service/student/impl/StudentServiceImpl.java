package com.sinjinsong.icourse.core.service.student.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.properties.AuthenticationProperties;
import com.sinjinsong.icourse.common.security.verification.VerificationManager;
import com.sinjinsong.icourse.common.util.UUIDUtil;
import com.sinjinsong.icourse.core.dao.role.RoleDOMapper;
import com.sinjinsong.icourse.core.dao.student.StudentDOMapper;
import com.sinjinsong.icourse.core.domain.dto.student.StudentDiscountDTO;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.enumeration.student.StudentStatus;
import com.sinjinsong.icourse.core.exception.user.UserNotFoundException;
import com.sinjinsong.icourse.core.exception.user.UsernameExistedException;
import com.sinjinsong.icourse.core.service.email.EmailService;
import com.sinjinsong.icourse.core.service.student.StudentService;
import com.sinjinsong.icourse.core.service.user.UserQueryService;
import com.sinjinsong.icourse.core.service.vip.VipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDOMapper studentDOMapper;
    @Autowired
    private RoleDOMapper roleDOMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private VipService vipService;
    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private AuthenticationProperties authenticationProperties;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationManager verificationManager;
        
    @Transactional(readOnly = true)
    @Override
    public StudentDO findByUsername(String username) {
        return studentDOMapper.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentDO findById(Long id) {
        return studentDOMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void save(StudentDO student) {
        if (userQueryService.exists(student.getUsername())) {
            throw new UsernameExistedException(student.getUsername());
        }
        //对密码进行加密
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRegTime(LocalDateTime.now());
        //设置用户状态为未激活
        student.setStatus(StudentStatus.UNACTIVATED);
        student.setConsumptions(0d);
        student.setVipLevel(1);
        studentDOMapper.insert(student);

        long roleId = roleDOMapper.findRoleIdByRoleName("ROLE_STUDENT");
        roleDOMapper.insertUserRole(student.getId(), roleId);
        
        log.info("email:{}",student.getEmail());
        
        String activationCode = UUIDUtil.uuid();
        //发送验证邮件
        verificationManager.createVerificationCode(activationCode, String.valueOf(student.getId()), authenticationProperties.getActivationCodeExpireTime());
        log.info("{}     {}", student.getEmail(), student.getId());
        Map<String, Object> params = new HashMap<>();
        params.put("id", student.getId());
        params.put("activationCode", activationCode);
        emailService.sendHTML(student.getEmail(), "activation", params, null);
    }

    @Transactional
    @Override
    public void update(StudentDO userDO) {
        userDO.setPassword(null);
        studentDOMapper.updateByPrimaryKeySelective(userDO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Transactional(readOnly = true)
    @Override
    public PageInfo<StudentDO> findAll(int pageNum, int pageSize) {
        return studentDOMapper.findAll(pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @Override
    public StudentDO findByEmail(String email) {
        return studentDOMapper.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public void incrementConsumptions(Long studentId, double value) {
        StudentDO studentDO = studentDOMapper.selectByPrimaryKey(studentId);
        if (studentDO == null) {
            throw new UserNotFoundException(String.valueOf(studentId));
        }
        studentDO.setConsumptions(studentDO.getConsumptions() + value);
        studentDO.setVipLevel(vipService.findVipLevelByConsumptions(studentDO.getConsumptions()));
        studentDOMapper.updateByPrimaryKeySelective(studentDO);
    }
    
    @Override
    public List<StudentDiscountDTO> findIdAndUsernameAndDiscountByUsernameContaining(String username) {
        List<StudentDiscountDTO> result = new ArrayList<>();
        studentDOMapper.findIdAndUsernameByUsernameContaining(username).forEach(student -> {
            result.add(new StudentDiscountDTO(student.getId(),student.getUsername(),
                    vipService.findDiscountByVipLevel(student.getVipLevel())
                    ));
        });
        return result;
    }
}
