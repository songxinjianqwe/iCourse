package com.sinjinsong.icourse.core.security.impl;


import com.sinjinsong.icourse.core.domain.dto.user.LoginDTO;
import com.sinjinsong.icourse.core.domain.entity.user.UserDO;
import com.sinjinsong.icourse.core.security.LoginHandler;
import com.sinjinsong.icourse.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Component("LoginHandler.phone")
public class PhoneLoginHandler implements LoginHandler {
    @Autowired
    private UserService service;
    
    @Override
    public UserDO handle(LoginDTO loginDTO) {
        return service.findByPhone(loginDTO.getPhone());
    }
}
