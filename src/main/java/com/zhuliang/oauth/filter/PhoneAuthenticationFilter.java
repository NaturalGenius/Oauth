package com.zhuliang.oauth.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.zhuliang.oauth.contact.LoginContact;
import com.zhuliang.oauth.token.PhoneAuthenticationToken;

/**
 * 自定义实现手机号验证码登录拦截器
 * @author MSI-PC
 *
 */
public class PhoneAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	public PhoneAuthenticationFilter() {
		super(new AntPathRequestMatcher(LoginContact.PHONT_LOGIN_PATH, "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String phone = obtainPhone(request);
		String verificationCode = obtainVerificationCode(request);

		if (phone == null) {
			phone = "";
		}

		if (verificationCode == null) {
			verificationCode = "";
		}

		phone = phone.trim();

        PhoneAuthenticationToken authRequest = new PhoneAuthenticationToken(phone, verificationCode);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 获取详细信息
	 * @param request
	 * @param authRequest
	 */
	private void setDetails(HttpServletRequest request, PhoneAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * 获取验证码
	 * @param request
	 * @return
	 */
	private String obtainVerificationCode(HttpServletRequest request) {
		return request.getParameter(LoginContact.PHONT_CODE_PARAM_NAME);
	}

	/**
	 * 获取手机号
	 * @param request
	 * @return
	 */
	private String obtainPhone(HttpServletRequest request) {
		return request.getParameter(LoginContact.PHONT_PARAM_NAME);
	}

}
