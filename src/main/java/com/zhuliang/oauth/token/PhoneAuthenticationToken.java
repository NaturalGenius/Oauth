package com.zhuliang.oauth.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义phone token
 * @author MSI-PC
 *
 */
public class PhoneAuthenticationToken extends UsernamePasswordAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhoneAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
	    super(principal, credentials, authorities);
	}

	public  PhoneAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
}
