package com.zhuliang.oauth.provider;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.exception.PhontVerificationCodeException;
import com.zhuliang.oauth.security.PhoneUserDetailsService;
import com.zhuliang.oauth.token.PhoneAuthenticationToken;

/**
 * 自定义实现手机号验证码登录
 * @author MSI-PC
 *
 */
@Component("phoneAuthenticationProvider")
public class PhoneAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private PhoneUserDetailsService phoneUserDetailsService;

	@Override
	public boolean supports(Class<?> authentication) {
		return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
	}

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
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
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        return phoneUserDetailsService.loadUserByUsername((String)authentication.getPrincipal());
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
            UserDetails user) {
        PhoneAuthenticationToken phoneAuthenticationToken = new PhoneAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());
        phoneAuthenticationToken.setDetails(authentication.getDetails());
        return phoneAuthenticationToken;
   }
}
