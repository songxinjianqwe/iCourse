package com.sinjinsong.icourse.core.service.institution.impl;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.dao.institution.InstitutionDOMapper;
import com.sinjinsong.icourse.core.domain.entity.institution.InstitutionDO;
import com.sinjinsong.icourse.core.service.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
public class InstitutionServiceImpl implements InstitutionService  {
    @Autowired
    private InstitutionDOMapper mapper;
    
    @Override
    public void save(InstitutionDO institutionDO) {
        mapper.insert(institutionDO);
    }

    @Override
    public void update(InstitutionDO institutionDO) {
        mapper.updateByPrimaryKeySelective(institutionDO);
    }

    @Override
    public PageInfo<InstitutionDO> findAll(int pageNum, int pageSize) {
        return mapper.findAll(pageNum,pageSize).toPageInfo();
    }

    @Override
    public InstitutionDO findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public InstitutionDO findByUsername(String username) {
        return mapper.findByUsername(username);
    }
}
