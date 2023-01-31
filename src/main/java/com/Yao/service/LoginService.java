package com.Yao.service;

import com.Yao.domain.ResponseResult;
import com.Yao.domain.User;

/**
 * Author Yjw
 * 2023/1/24 23:25
 */
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
