package com.sinjinsong.icourse.core.service.user;


import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.core.domain.entity.user.UserDO;

import java.util.List;

/**
 * Created by SinjinSong on 2017/4/27.
 */
public interface UserService {
    UserDO findByUsername(String username);
    UserDO findByPhone(String phone);
    UserDO findById(Long id);
    void save(UserDO userDO);
    void update(UserDO userDO);
    PageInfo<UserDO> findAll(int pageNum, int pageSize);
    String findAvatarById(Long id);
    UserDO findByEmail(String email);
    void resetPassword(Long id, String username, String newPassword);
    List<UserDO> findIdAndNameByUsernameContaining(String username);
}
