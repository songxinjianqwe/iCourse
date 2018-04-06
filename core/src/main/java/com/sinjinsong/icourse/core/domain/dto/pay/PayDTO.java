package com.sinjinsong.icourse.core.domain.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author sinjinsong
 * @date 2017/12/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDTO {
    @NotNull
    private String alipayUsername;
    @NotNull
    private String paymentPassword;
}
