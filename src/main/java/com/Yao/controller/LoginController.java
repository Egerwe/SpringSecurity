package com.Yao.controller;

import com.Yao.domain.ResponseResult;
import com.Yao.domain.User;
import com.Yao.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author Yjw
 * 2023/1/24 23:22
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        //登录
        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
