package com.sinjinsong.icourse.core.service.manager.impl;

import com.sinjinsong.icourse.core.dao.manager.ManagerDOMapper;
import com.sinjinsong.icourse.core.domain.entity.manager.ManagerDO;
import com.sinjinsong.icourse.core.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
public class ManagerServiceImpl implements ManagerService  {
    @Autowired
    private ManagerDOMapper mapper;
    
    @Override
    public ManagerDO findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public ManagerDO findByUsername(String username) {
        return mapper.findByUsername(username);
    }
}
