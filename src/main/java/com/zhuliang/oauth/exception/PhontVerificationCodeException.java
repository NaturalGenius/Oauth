package com.zhuliang.oauth.exception;

import org.springframework.security.core.AuthenticationException;

public class PhontVerificationCodeException extends AuthenticationException{

    private static final long serialVersionUID = 1L;

    public PhontVerificationCodeException() {
        super("手机号验证码失败");
    }
    public PhontVerificationCodeException(String msg) {
    	super(msg);
    }

}
