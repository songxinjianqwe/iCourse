package com.sinjinsong.icourse.core.service.institution.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.institution.InstitutionDOMapper;
import com.sinjinsong.icourse.core.dao.role.RoleDOMapper;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;
import com.sinjinsong.icourse.core.enumeration.institution.InstitutionStatus;
import com.sinjinsong.icourse.core.exception.user.UsernameExistedException;
import com.sinjinsong.icourse.core.service.institution.InstitutionService;
import com.sinjinsong.icourse.core.service.order.OrderService;
import com.sinjinsong.icourse.core.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
public class InstitutionServiceImpl implements InstitutionService {
    @Autowired
    private InstitutionDOMapper institutionDOMapper;
    @Autowired
    private RoleDOMapper roleDOMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private OrderService orderService;

    @Transactional
    @Override
    public void register(InstitutionDO institutionDO) {
        if (userQueryService.exists(institutionDO.getUsername())) {
            throw new UsernameExistedException(institutionDO.getUsername());
        }
        institutionDO.setStatus(InstitutionStatus.NOT_APPROVED);
        institutionDO.setPassword(passwordEncoder.encode(institutionDO.getPassword()));
        institutionDOMapper.insert(institutionDO);
        long roleId = roleDOMapper.findRoleIdByRoleName("ROLE_INSTITUTION");
        roleDOMapper.insertUserRole(institutionDO.getId(), roleId);
    }

    @Transactional
    @Override
    public void update(InstitutionDO institutionDO) {
        institutionDO.setPassword(null);
        institutionDOMapper.updateByPrimaryKeySelective(institutionDO);
    }
    
    @Transactional(readOnly = true)
    @Override
    public PageInfo<InstitutionDO> findAll(int pageNum, int pageSize) {
        return institutionDOMapper.findAll(pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<InstitutionDO> findAllByStatus(InstitutionStatus status, int pageNum, int pageSize) {
        return institutionDOMapper.findAllByStatus(status, pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @Override
    public InstitutionDO findById(Long id) {
        return institutionDOMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public InstitutionDO findByUsername(String username) {
        return institutionDOMapper.findByUsername(username);
    }

    @Transactional
    @Override
    public void approveBatch(List<Long> institutionIds) {
        institutionDOMapper.updateStatusBatch(institutionIds, InstitutionStatus.APPROVED);
    }

    @Override
    public InstitutionDO findInstitutionByClassId(Long classId) {
        return institutionDOMapper.findByClassId(classId);
    }

    @Override
    public InstitutionDO findInstitutionByOrderId(Long orderId) {
        return findInstitutionByClassId(orderService.findById(orderId).getClassDO().getId());
    }

}


