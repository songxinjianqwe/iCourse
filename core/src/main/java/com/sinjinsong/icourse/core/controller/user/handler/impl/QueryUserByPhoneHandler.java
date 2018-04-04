package com.sinjinsong.icourse.core.controller.user.handler.impl;


import com.sinjinsong.icourse.core.controller.user.handler.QueryUserHandler;
import com.sinjinsong.icourse.core.domain.entity.user.UserDO;
import com.sinjinsong.icourse.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/5/6.
 */
@Component("QueryUserHandler.phone")
public class QueryUserByPhoneHandler implements QueryUserHandler {
    @Autowired
    private UserService userService;
    @Override
    public UserDO handle(String key) {
        return userService.findByEmail(key);
    }
}
