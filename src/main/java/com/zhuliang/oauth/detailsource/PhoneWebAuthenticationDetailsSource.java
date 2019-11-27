package com.zhuliang.oauth.detailsource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

/**
 * 自定义WebAuthenticationDetailsSource
 * @author MSI-PC
 *
 */
@Component("phoneWebAuthenticationDetailsSource")
public class PhoneWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource{

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new PhoneWebAuthenticationDetails(context);
	}
	
}
