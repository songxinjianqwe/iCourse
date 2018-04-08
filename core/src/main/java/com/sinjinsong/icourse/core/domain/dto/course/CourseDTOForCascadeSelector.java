package com.sinjinsong.icourse.core.domain.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTOForCascadeSelector {
    private String label;
    private Long value;
    private List<ClassDTOForCascadeSelector> children;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClassDTOForCascadeSelector {
        private String label;
        private Long value;
        private Double price;
    }
}
