package com.sinjinsong.icourse.core.service.user;

import com.sinjinsong.icourse.core.domain.dto.user.AbstractUserDTO;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public interface UserQueryService {
    AbstractUserDTO findByUsername(String username);
    boolean exists(String username);
}
