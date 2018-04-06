package com.sinjinsong.icourse.core.controller.user;

import com.sinjinsong.icourse.core.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserQueryService userQueryService;
    
    @GetMapping("/{username}/duplication")
    public boolean exists(@PathVariable("username") String username) {
        return userQueryService.exists(username);
    }
}
