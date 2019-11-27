package com.zhuliang.oauth.detailsource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class PhoneWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;
	
	public PhoneWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
	}
}
