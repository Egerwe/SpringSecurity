package com.Yao.service.impl;

import com.Yao.domain.LoginUser;
import com.Yao.domain.User;
import com.Yao.mapper.MenuMapper;
import com.Yao.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Author Yjw
 * 2023/1/24 15:13
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO 查询对应的权限信息
        //List<String> list = new ArrayList<>(Arrays.asList("test"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //把数据封装成 UserDetails 返回
        return new LoginUser(user, list);
    }
}
