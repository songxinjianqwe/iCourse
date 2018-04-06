package com.sinjinsong.icourse.common.converter;

import com.sinjinsong.icourse.common.security.domain.JwtUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

/**
 * Created by SinjinSong on 2017/7/28.
 */
public class UserConverter {
    public static JwtUser convertToUser(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        return (JwtUser) token.getPrincipal();
    }
}
