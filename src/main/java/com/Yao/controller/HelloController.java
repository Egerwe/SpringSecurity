package com.Yao.controller;

import com.Yao.domain.ResponseResult;
import com.Yao.mapper.UserMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Author Yjw
 * 2023/1/22 22:46
 */
@RestController
public class HelloController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list1')")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200, "testCors");
    }

    @GetMapping("/test")
    public ResponseResult test() {

        String userPassword = "1234";
        System.out.println("userPassword = " + userPassword);

        String DbPassword = userMapper.selectById(2).getPassword();
        System.out.println("DbPassword = " + DbPassword);

        if (passwordEncoder.matches(userPassword, DbPassword)) {
            System.out.println("用户输入的密码等于数据库查出来的密码");
        } else {
            System.out.println("用户输入的密码不等于数据库查出来的密码");
        }

        return new ResponseResult(1, "成功");
    }
}
