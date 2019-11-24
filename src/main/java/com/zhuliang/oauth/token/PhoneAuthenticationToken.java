package com.zhuliang.oauth.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义phone token
 * @author MSI-PC
 *
 */
public class PhoneAuthenticationToken extends AbstractAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Object principal;
	private Object credentials;
	
	public PhoneAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); 
	}

	public  PhoneAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}
