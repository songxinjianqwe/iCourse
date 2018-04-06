package com.sinjinsong.icourse.core.domain.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinjinsong.icourse.common.properties.DateTimeProperties;
import com.sinjinsong.icourse.core.enumeration.course.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long institutionId;
    @NotNull
    private String name;
    @NotNull
    private CourseType type;
    @NotNull
    private String description;
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private List<ClassDTO> classes;
}
