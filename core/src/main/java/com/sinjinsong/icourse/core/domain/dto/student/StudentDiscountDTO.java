package com.sinjinsong.icourse.core.domain.dto.student;

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
public class StudentDiscountDTO {
    private Long id;
    private String username;
    private Double discount;
}
