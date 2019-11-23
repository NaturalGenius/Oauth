package com.zhuliang.oauth.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;
import com.zhuliang.oauth.contact.KaptchaContact;

/**
 * 验证吗控制器
 * @author zhuliang
 * @date 2019年11月22日
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Producer producer;
    
    @GetMapping("/image")
    public void getKaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        String text = producer.createText();
        request.getSession().setAttribute(KaptchaContact.VERIFICATION_CODE, text);
        BufferedImage createImage = producer.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(createImage, "jpg", outputStream);
        try {
            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }
}
