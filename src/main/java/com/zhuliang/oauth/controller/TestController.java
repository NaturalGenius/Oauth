package com.zhuliang.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String testSpringSecurity() {
        return "你好 spring security";
    }
    @GetMapping(value = "/download_temp")
    public void downloadTemp(HttpServletRequest request, HttpServletResponse response)
        throws IORuntimeException, IOException {
        String fileName = "ttt.xlsx";
        Resource resource = new ClassPathResource("static/" + fileName);
        // 设置输出的格式
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Access-Control-Allow-Origin", "*");
        IoUtil.copy(resource.getInputStream(), response.getOutputStream());
    }
}
