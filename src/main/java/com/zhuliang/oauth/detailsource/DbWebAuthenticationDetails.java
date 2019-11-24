package com.zhuliang.oauth.detailsource;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.zhuliang.oauth.contact.KaptchaContact;

public class DbWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;
	private boolean imageCodeIsRight;
	
	public DbWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		String kaptcha = request.getParameter(KaptchaContact.KAPTCHA_PARAM_NAME);
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute(KaptchaContact.VERIFICATION_CODE);
		if (StringUtils.isNotEmpty(code)) {
			// 验证码验证一次需要清除
			session.removeAttribute(KaptchaContact.VERIFICATION_CODE);
		}
		if (StringUtils.isNotEmpty(kaptcha) && StringUtils.isNotEmpty(code) && Objects.equals(kaptcha, code)) {
			// 验证码正确
			this.imageCodeIsRight = true;
		}
	}

	public boolean isImageCodeIsRight() {
		return imageCodeIsRight;
	}

}
