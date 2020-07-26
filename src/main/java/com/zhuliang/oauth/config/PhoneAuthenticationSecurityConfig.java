package com.zhuliang.oauth.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.filter.PhoneAuthenticationFilter;
import com.zhuliang.oauth.handle.ServerAuthenticationFailureHandler;
import com.zhuliang.oauth.handle.ServerAuthenticationSuccessHandler;
import com.zhuliang.oauth.provider.PhoneAuthenticationProvider;


/**
 * 重写手机号验证码认证配置
 * @author wangshouyu
 */
@Component
public class PhoneAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Autowired
	@Qualifier("phoneAuthenticationProvider")
	private PhoneAuthenticationProvider phoneAuthenticationProvider;
	
	@Autowired
    @Qualifier("phoneWebAuthenticationDetailsSource")
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> phoneWebAuthenticationDetailsSource;
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		 PhoneAuthenticationFilter phoneAuthenticationFilter = new PhoneAuthenticationFilter();
         phoneAuthenticationFilter.setAuthenticationFailureHandler(new ServerAuthenticationFailureHandler());
         phoneAuthenticationFilter.setAuthenticationSuccessHandler(new ServerAuthenticationSuccessHandler());
         phoneAuthenticationFilter.setAuthenticationDetailsSource(phoneWebAuthenticationDetailsSource);
         //获取容器中已经存在的认证管理器
         phoneAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
         http
         .authenticationProvider(phoneAuthenticationProvider)
         .addFilterAfter(phoneAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
         super.configure(http);
	}

}
