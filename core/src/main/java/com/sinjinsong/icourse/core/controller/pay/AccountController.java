package com.sinjinsong.icourse.core.controller.pay;

import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.security.domain.JwtUser;
import com.sinjinsong.icourse.core.domain.dto.pay.PayDTO;
import com.sinjinsong.icourse.core.service.pay.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bind")
    public void bindAccount(@RequestBody @Valid PayDTO payDTO, BindingResult bindingResult, @AuthenticationPrincipal JwtUser student) {
        if (bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        accountService.bind(student.getId(), payDTO);
    }
    
    @PostMapping("/pay/{orderId}")
    public void pay(@PathVariable("orderId") Long orderId, @RequestBody @Valid PayDTO payDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RestValidationException(bindingResult.getFieldErrors());
        }
        accountService.pay(orderId,payDTO);
    }
}
