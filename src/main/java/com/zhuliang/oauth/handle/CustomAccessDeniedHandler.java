package com.zhuliang.oauth.handle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.enums.GlobalResultCode;
/**
 * 无权限权限访问异常处理类
 * @author zhuliang
 * @date 2019年12月3日
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        writer.write(JSON.toJSONString(Result.newInstance().setResultCode(GlobalResultCode.FORBIDDEN)
                .setMessage(accessDeniedException.getMessage())));
    }

}
