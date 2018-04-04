package com.sinjinsong.icourse.core.security;


import com.sinjinsong.icourse.core.domain.dto.user.LoginDTO;
import com.sinjinsong.icourse.core.domain.entity.user.UserDO;

/**
 * Created by SinjinSong on 2017/5/7.
 */
public interface LoginHandler {
    UserDO handle(LoginDTO loginDTO);
}
