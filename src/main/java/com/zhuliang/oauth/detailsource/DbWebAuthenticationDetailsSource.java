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
@Component("dbWebAuthenticationDetailsSource")
public class DbWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource{

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new DbWebAuthenticationDetails(context);
	}
	
}
