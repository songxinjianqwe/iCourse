package com.sinjinsong.icourse.core.domain.dto.user;

import com.sinjinsong.icourse.core.domain.entity.role.RoleDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbstractUserDTO {
    private Long id;
    private String username;
    private String password;
    private List<RoleDO> roles;
    private boolean isValid;
}
