package com.zhuliang.oauth.result.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

//@RestController
public class UrlErrorHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public void error(HttpServletResponse resp, HttpServletRequest req) throws NoHandlerFoundException {
        // 错误处理逻辑
        throw new NoHandlerFoundException(req.getMethod(), req.getServletPath(), null);
    }
}
