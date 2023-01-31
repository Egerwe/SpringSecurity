package com.Yao.service.impl;

import com.Yao.domain.LoginUser;
import com.Yao.domain.ResponseResult;
import com.Yao.domain.User;
import com.Yao.service.LoginService;
import com.Yao.utils.JwtUtil;
import com.Yao.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author Yjw
 * 2023/1/24 23:26
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        //AuthenticationManager authenticate 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用 userId 生产一个 jwt ，jwt 存入ResponseResult 返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整的用户信息存入 redis ，userId 作为 key
        redisCache.setCacheObject("login" + userId, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取 SecurityContextHolder 中的用户 id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        //删除 redis 中的值
        redisCache.deleteObject("login" + id);
        return new ResponseResult(200, "注销成功");
    }
}
