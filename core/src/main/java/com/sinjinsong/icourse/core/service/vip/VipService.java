package com.sinjinsong.icourse.core.service.vip;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface VipService {
    double findDiscountByVipLevel(int vipLevel);

    int findVipLevelByConsumptions(double consumptions);
}
