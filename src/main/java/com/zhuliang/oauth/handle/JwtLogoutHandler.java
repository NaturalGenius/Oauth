 package com.zhuliang.oauth.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.zhuliang.oauth.util.JwtTokenUtil;

public class JwtLogoutHandler implements LogoutHandler{

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
         String tokenFromRequest = JwtTokenUtil.getTokenFromRequest(request);
         if (StringUtils.isNoneBlank(tokenFromRequest)) {
             //TODO jwttoken 的登出逻辑
        }
    }

}
