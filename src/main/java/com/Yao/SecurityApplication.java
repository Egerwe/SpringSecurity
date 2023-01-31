package com.Yao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Author Yjw
 * 2023/1/22 22:44
 */
@SpringBootApplication
@MapperScan("com.Yao.mapper")
public class SecurityApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SecurityApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
