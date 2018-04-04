package com.sinjinsong.icourse.core.service.order.impl;


import com.sinjinsong.icourse.core.dao.order.EnterpriseDOMapper;
import com.sinjinsong.icourse.core.domain.entity.order.EnterpriseDO;
import com.sinjinsong.icourse.core.service.order.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sinjinsong
 * @date 2017/12/23
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseDOMapper mapper;
    
    @Override
    public EnterpriseDO find() {
        return mapper.selectByPrimaryKey(1L);
    }

    @Override
    public int update(EnterpriseDO enterpriseDO) {
        return mapper.updateByPrimaryKeySelective(enterpriseDO);
    }
}
