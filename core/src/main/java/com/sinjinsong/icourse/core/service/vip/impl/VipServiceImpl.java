package com.sinjinsong.icourse.core.service.vip.impl;

import com.sinjinsong.icourse.core.dao.vip.VIPDiscountDOMapper;
import com.sinjinsong.icourse.core.service.vip.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VIPDiscountDOMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public double findDiscountByVipLevel(int vipLevel) {
        return mapper.findDiscountByVipLevel(vipLevel);
    }

    @Transactional(readOnly = true)
    @Override
    public int findVipLevelByConsumptions(double consumptions) {
        return mapper.findVipLevelByConsumptions(consumptions);
    }
}
