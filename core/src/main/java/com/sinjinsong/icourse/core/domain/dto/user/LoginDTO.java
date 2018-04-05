package com.sinjinsong.icourse.core.domain.dto.user;


import com.sinjinsong.icourse.core.enumeration.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable{
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private UserType userType;
}
