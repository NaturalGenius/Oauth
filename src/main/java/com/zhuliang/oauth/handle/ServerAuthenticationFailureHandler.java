package com.zhuliang.oauth.handle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.alibaba.fastjson.JSON;
import com.zhuliang.oauth.contact.LoginContact;
import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.enums.GlobalResultCode;

/**
 * 验证失败处理器
 * @author zhuliang
 * @date 2019年11月22日
 */
public class ServerAuthenticationFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        //response.sendRedirect(LoginContact.LOGIN_PATH);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(Result.newInstance().setResultCode(GlobalResultCode.LOGIN_ERROR)
                .setMessage(exception.getMessage())));
    }

}
