package com.guihang.medicalbackend.controller;

import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.AccountDTO;
import com.guihang.medicalbackend.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户模块", description = "用户接口")
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "登录时传入的参数可以封装到AccountDTO对象中")
    public JSONResult login(@RequestBody AccountDTO accountDTO) {
        System.out.println(accountDTO);
        return accountService.login(accountDTO);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册",description = "注册时传入的参数可以封装到AccountDTO对象中")
    public JSONResult register(@RequestBody AccountDTO accountDTO){
        return accountService.register(accountDTO);
    }

//    @PostMapping("/findUserByName")
//    @Operation(summary = "根据Name查询用户",description = "查询时传入的参数可以封装到AccountDTO对象中")
//    public JSONResult findUserByName(@RequestBody AccountDTO accountDTO){
//        return accountService.findUserByName(accountDTO.getUname());
//    }



}
