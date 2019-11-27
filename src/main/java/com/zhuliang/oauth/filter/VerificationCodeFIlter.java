package com.zhuliang.oauth.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zhuliang.oauth.contact.KaptchaContact;
import com.zhuliang.oauth.contact.LoginContact;
import com.zhuliang.oauth.exception.VerificationCodeException;
import com.zhuliang.oauth.handle.ServerAuthenticationFailureHandler;

/**
 * 验证码拦截器  初级实现方法
 * @author zhuliang
 * @date 2019年11月22日
 */
@Component
public class VerificationCodeFIlter extends OncePerRequestFilter{

    private AuthenticationFailureHandler authenticationFailureHandler = new ServerAuthenticationFailureHandler();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
        	if (Objects.equals(LoginContact.LOGIN_PATH, request.getServletPath())) {
        		verificationCode(request, response);
			}
            filterChain.doFilter(request, response);
        } catch (VerificationCodeException e) {
            authenticationFailureHandler.onAuthenticationFailure(request, response, e);
        }
    }

    private void verificationCode(HttpServletRequest request, HttpServletResponse response) {
        String kaptcha = request.getParameter(KaptchaContact.KAPTCHA_PARAM_NAME);
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(KaptchaContact.VERIFICATION_CODE);
        if (StringUtils.isNotEmpty(code)) {
            //验证码验证一次需要清除
            session.removeAttribute(KaptchaContact.VERIFICATION_CODE);
        }
        if (StringUtils.isEmpty(kaptcha) || StringUtils.isEmpty(code) || !Objects.equals(kaptcha, code)) {
            //验证码不正确抛出异常
            throw new VerificationCodeException();
        }
    }
}
