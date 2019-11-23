package com.zhuliang.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zhuliang.oauth.mapper")
public class OAhthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAhthApplication.class, args);
    }

}
