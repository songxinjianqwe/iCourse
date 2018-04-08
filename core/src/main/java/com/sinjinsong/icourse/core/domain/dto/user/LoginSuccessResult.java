package com.sinjinsong.icourse.core.domain.dto.user;

import com.sinjinsong.icourse.core.domain.entity.role.RoleDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SinjinSong on 2017/10/11.
 */
@Data
public class LoginSuccessResult implements Serializable{
    private Long id;
    private String username;
    private String token;
    private String role;
    
    public LoginSuccessResult(Long id, String username, String token, List<RoleDO> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.role = roles.get(0).getRoleName().replace("ROLE_","").toLowerCase();
    }
}
