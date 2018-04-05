package com.sinjinsong.icourse.core.service.pay;

import com.sinjinsong.icourse.core.domain.dto.pay.PayDTO;
import com.sinjinsong.icourse.core.domain.entity.pay.AlipayDO;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface PayService {
    void bind(AlipayDO aliPayDO);

    void deposit(Long userId, PayDTO alipayDO, int amount);

    void pay(Long orderId, PayDTO payDTO);
}
