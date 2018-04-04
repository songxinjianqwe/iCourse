package com.sinjinsong.icourse.common.converter;

import com.sinjinsong.icourse.common.security.domain.JWTUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

/**
 * Created by SinjinSong on 2017/7/28.
 */
public class UserConverter {
    public static JWTUser convertToUser(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        return (JWTUser) token.getPrincipal();
    }
}
