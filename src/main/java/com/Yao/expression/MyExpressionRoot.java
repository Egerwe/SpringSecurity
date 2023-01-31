package com.Yao.expression;

import com.Yao.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author Yjw
 * 2023/1/28 22:40
 */
@Component("ex")
public class MyExpressionRoot {

    public boolean hasAuthority(String authority) {

        //获取当前用户的权限
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在 authority
        return permissions.contains(authority);
    }
}
