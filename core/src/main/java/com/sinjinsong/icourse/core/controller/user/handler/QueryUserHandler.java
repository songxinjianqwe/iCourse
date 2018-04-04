package com.sinjinsong.icourse.core.controller.user.handler;


import com.sinjinsong.icourse.core.domain.entity.user.UserDO;

/**
 * Created by SinjinSong on 2017/4/27.
 */
public interface QueryUserHandler {
    UserDO handle(String key);
}
