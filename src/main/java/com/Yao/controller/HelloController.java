package com.Yao.controller;

import com.Yao.domain.ResponseResult;
import com.Yao.domain.User;
import com.Yao.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author Yjw
 * 2023/1/22 22:46
 */
@RestController
@Slf4j
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

    @PostMapping("/test0")
    public User test0(@RequestBody User user) {

        log.info(JSON.toJSONString(user));
        return user;
    }

    @GetMapping("/test")
    public User test() {
        User user = userMapper.selectById(2);
        log.info(JSON.toJSONString(user));
        return user;
    }
}
