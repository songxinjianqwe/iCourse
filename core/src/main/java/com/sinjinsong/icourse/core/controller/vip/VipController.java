package com.sinjinsong.icourse.core.controller.vip;

import com.sinjinsong.icourse.common.security.domain.JwtUser;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.service.student.StudentService;
import com.sinjinsong.icourse.core.service.vip.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestController
@RequestMapping("/vip")
public class VipController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private VipService vipService;
    
    @GetMapping("/discount")
    public Double getDiscountedPrice(@AuthenticationPrincipal JwtUser user) {
        StudentDO student = studentService.findById(user.getId());
        return vipService.findDiscountByVipLevel(student.getVipLevel());
    }
}
