package com.sinjinsong.icourse.core.service.order;


import com.sinjinsong.icourse.core.domain.entity.order.EnterpriseDO;

/**
 * @author sinjinsong
 * @date 2017/12/23
 */
public interface EnterpriseService {
    EnterpriseDO find();
    int update(EnterpriseDO enterpriseDO);
}
