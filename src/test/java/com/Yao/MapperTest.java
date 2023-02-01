package com.Yao;

import com.Yao.domain.ResponseResult;
import com.Yao.domain.User;
import com.Yao.mapper.MenuMapper;
import com.Yao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Author Yjw
 * 2023/1/24 15:09
 */
@SpringBootTest
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void testUserMapper() {
        User user = new User().setUserName("Xu").setPassword("12345");
        userMapper.insert(user);
    }

    @Test
    public void add() {
        User user = new User();
        user.setUserName("Xu");
        user.setPassword("1234");
        userMapper.insert(user);
    }

    @Test
    public void testBCryptPasswordEncoder() {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //$2a$10$hysMh129Zx.YrsqE.lvLwuFjtuE7u6mOEIlf56qaQNtjtYPTBEWBi
        //$2a$10$BQwkxKOzmBMRVjRxrSJzGuwQyA5sgwP0GNizrXrtNQT/fol.V9m3C
        System.out.println(passwordEncoder.
                matches("1234",
                        "$2a$10$hysMh129Zx.YrsqE.lvLwuFjtuE7u6mOEIlf56qaQNtjtYPTBEWBi"));

        //String encode = encoder.encode("1234");
        //String encode2 = encoder.encode("1234");
        //System.out.println(encode);
        //System.out.println(encode2);
    }

    @Test
    public void testSelectPermsByUserId() {
        System.out.println(menuMapper.selectPermsByUserId(2L));
    }

    @Test
    public void testBC() {
        User user = new User();
        System.out.println(userMapper.selectById(2).getPassword());

    }

}
