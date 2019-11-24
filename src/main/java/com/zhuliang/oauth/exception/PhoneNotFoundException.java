package com.zhuliang.oauth.exception;

import org.springframework.security.core.AuthenticationException;

public class PhoneNotFoundException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhoneNotFoundException () {
		super("手机号不存在");
	}

}
