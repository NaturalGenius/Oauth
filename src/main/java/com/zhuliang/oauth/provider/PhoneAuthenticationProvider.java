package com.zhuliang.oauth.provider;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.exception.PhoneNotFoundException;
import com.zhuliang.oauth.exception.PhontVerificationCodeException;
import com.zhuliang.oauth.security.PhoneUserDetailsService;
import com.zhuliang.oauth.token.PhoneAuthenticationToken;

/**
 * 自定义实现手机号验证码登录
 * @author MSI-PC
 *
 */
@Component("phoneAuthenticationProvider")
public class PhoneAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private PhoneUserDetailsService phoneUserDetailsService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = phoneUserDetailsService.loadUserByUsername((String)authentication.getPrincipal());
		if (userDetails == null) {
			throw new PhoneNotFoundException();
		}
		String verificationCode = (String) authentication.getCredentials();
		//此出从从放验证码的地方获取
		String code = "8888";
		if (StringUtils.isEmpty(code)) {
			throw new PhontVerificationCodeException("验证码失效");
		}
		if (StringUtils.isEmpty(verificationCode)) {
			throw new PhontVerificationCodeException("验证码不能为空");
		}
		if (!Objects.equals(verificationCode, code)) {
		    throw new PhontVerificationCodeException("验证码不正确");
		}
		PhoneAuthenticationToken phoneAuthenticationToken = new PhoneAuthenticationToken(authentication.getPrincipal(), code, userDetails.getAuthorities());
		phoneAuthenticationToken.setDetails(authentication.getDetails());
		return phoneAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
