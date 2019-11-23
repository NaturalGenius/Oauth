package com.zhuliang.oauth.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

	/**
	 * 返回当前登录的用户对象
	 * @return
	 */
	public static UserDetails getUserDetail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		return null;
	}
}
