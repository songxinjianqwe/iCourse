package com.sinjinsong.icourse.core.service.student.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.role.RoleDOMapper;
import com.sinjinsong.icourse.core.dao.student.StudentDOMapper;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.enumeration.student.StudentStatus;
import com.sinjinsong.icourse.core.exception.user.UserNotFoundException;
import com.sinjinsong.icourse.core.exception.user.UsernameExistedException;
import com.sinjinsong.icourse.core.service.student.StudentService;
import com.sinjinsong.icourse.core.service.user.UserQueryService;
import com.sinjinsong.icourse.core.service.vip.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
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
    public void save(StudentDO studentDO) {
        if (userQueryService.exists(studentDO.getUsername())) {
            throw new UsernameExistedException(studentDO.getUsername());
        }
        //对密码进行加密
        studentDO.setPassword(passwordEncoder.encode(studentDO.getPassword()));
        studentDO.setRegTime(LocalDateTime.now());
        //设置用户状态为未激活
        studentDO.setStatus(StudentStatus.UNACTIVATED);
        studentDO.setConsumptions(0d);
        studentDO.setVipLevel(1);
        studentDOMapper.insert(studentDO);

        long roleId = roleDOMapper.findRoleIdByRoleName("ROLE_STUDENT");
        roleDOMapper.insertUserRole(studentDO.getId(), roleId);
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
}
