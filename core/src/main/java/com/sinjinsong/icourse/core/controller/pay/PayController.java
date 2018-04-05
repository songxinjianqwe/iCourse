package com.sinjinsong.icourse.core.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    
    @PostMapping("/bind")
    public void bindAccount(){
        
    }
}
