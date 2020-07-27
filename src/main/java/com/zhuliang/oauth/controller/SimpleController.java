package com.zhuliang.oauth.controller;

import com.alibaba.fastjson.JSON;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SimpleController {

    @GetMapping("/hello")
    public String hello(Principal principal) {
        return JSON.toJSONString(principal);
    }
}
