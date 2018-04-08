package com.sinjisong.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sinjinsong
 * @date 2018/4/8
 */
public class Test {
    @org.junit.Test
    public void test(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("student"));
    }
}
