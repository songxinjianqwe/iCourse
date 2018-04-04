package com.sinjinsong.icourse.core.domain.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinjinsong.icourse.core.enumeration.order.OrderStatus;
import com.sinjinsong.icourse.common.properties.DateTimeProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by SinjinSong on 2017/10/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderQueryConditionDTO implements Serializable{
    private Long userId;
    private Long categoryId;
    private List<Long> productIds;
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime begin;
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime end;
    private OrderStatus status;
    private Integer pageNum;
    private Integer pageSize;
}
