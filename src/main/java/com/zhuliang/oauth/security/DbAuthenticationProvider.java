package com.zhuliang.oauth.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 自定义实现数据库鉴权
 * @author zhuliang
 * @date 2019年11月22日
 */
@Component
public class DbAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private DbUserDetailsService dbUserDetailsService;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetail user = (UserDetail) dbUserDetailsService.loadUserByUsername(username);
        if(user == null){
            throw new BadCredentialsException("Username not found.");
        }

        //加密过程在这里体现
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
