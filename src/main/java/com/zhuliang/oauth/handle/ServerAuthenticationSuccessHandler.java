package com.zhuliang.oauth.handle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.alibaba.fastjson.JSON;
import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.enums.GlobalResultCode;

public class ServerAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(
                Result.newInstance().setResultCode(GlobalResultCode.SUCCESS).setData("欢迎访问系统")));		
	}

}
