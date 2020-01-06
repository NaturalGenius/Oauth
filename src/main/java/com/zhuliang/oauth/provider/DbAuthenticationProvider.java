package com.zhuliang.oauth.provider;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.exception.VerificationCodeException;
import com.zhuliang.oauth.security.DbUserDetailsService;
import com.zhuliang.oauth.source.detail.DbWebAuthenticationDetails;

/**
 * 用户名密码登陆 新增验证码验证逻辑 由于spring security 已经提供了
 * 用户名密码登陆完整的验证流程 故只需要新增验证码的逻辑即可
 * @author zhuliang
 * @date 2019年11月22日
 */
@Component("dbAuthenticationProvider")
public class DbAuthenticationProvider extends DaoAuthenticationProvider{

	public DbAuthenticationProvider(DbUserDetailsService dbUserDetailsService, PasswordEncoder passwordEncoder) {
		setUserDetailsService(dbUserDetailsService);
		setPasswordEncoder(passwordEncoder);
	}
	
	/**
	 * 添加附件验证流程
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		//校验验证码  由于验证码在httpservletrequest对象中 如何获取httpservletrequest对象
		// Authentication Object getDetails(); 允许携带任意对象
		//AuthenticationProvider 是由ProviderManager管理的 而ProviderManager
		//是由AbstractAuthenticationProcessingFilter调用
		//也就是说AuthenticationProvider包含的Authentication都来源于AbstractAuthenticationProcessingFilter
		//AbstractAuthenticationProcessingFilter并没有用户的详细的信息 而是通过标准接口
		//AuthenticationDetailsSource构建的 这意味着它是允许定制的特性 
		//org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.setDetails(HttpServletRequest, UsernamePasswordAuthenticationToken)
		//故可以实现 
		DbWebAuthenticationDetails details = (DbWebAuthenticationDetails) authentication.getDetails();
		if (!details.isImageCodeIsRight()) {
			//验证码不正确抛出异常
			throw new VerificationCodeException();
		}
		//调用父类实现密码验证
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
	     return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}
}
