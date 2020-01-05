package com.zhuliang.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhuliang.oauth.entity.User;
import com.zhuliang.oauth.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public String testSpringSecurity() {
        return "你好 spring security user";
    }
    
    @GetMapping("/save")
    public User saveUser() {
        User user = new User();
        user.setAccount("zhangsan");
        user.setAge(12);
        user.setNickName("张三");
        userService.saveUser(user);
        return user;
    }
}
