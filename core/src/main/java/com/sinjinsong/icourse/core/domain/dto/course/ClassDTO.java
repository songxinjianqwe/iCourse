package com.sinjinsong.icourse.core.domain.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer threshold;
}
