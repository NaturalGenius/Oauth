package com.zhuliang.oauth.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException{

    private static final long serialVersionUID = 1L;

    public VerificationCodeException() {
        super("图形验证码验证失败");
    }

}
