package com.sinjinsong.icourse.core.controller.user.handler.impl;


import com.sinjinsong.icourse.core.controller.user.handler.QueryUserHandler;
import com.sinjinsong.icourse.core.domain.entity.user.UserDO;
import com.sinjinsong.icourse.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Component("QueryUserHandler.id")
public class QueryUserByIdHandler implements QueryUserHandler {
    @Autowired
    private UserService service;
    @Override
    public UserDO handle(String key) {
        return service.findById(Long.parseLong(key));
    }
}
