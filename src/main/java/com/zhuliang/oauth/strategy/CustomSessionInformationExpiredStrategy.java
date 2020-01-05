package com.zhuliang.oauth.strategy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.enums.GlobalResultCode;

@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy{

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.newInstance().setCode(GlobalResultCode.LOGIN_ERROR.code()).setMessage("您的账号已在别处登录")));
    }

}
