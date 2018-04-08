package com.sinjinsong.icourse.core.domain.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sinjinsong
 * @date 2018/4/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResult {
    private Boolean isBound;
    private Double balance;
}
