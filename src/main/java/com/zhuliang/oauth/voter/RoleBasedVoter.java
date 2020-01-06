package com.zhuliang.oauth.voter;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.zhuliang.oauth.manage.CustomAccessDecisionManager;

public class RoleBasedVoter implements AccessDecisionVoter<Object> {
	private static Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		logger.info("[资源权限]: {}", attributes);
		logger.info("[用户权限]: {}", authentication.getAuthorities());

		Iterator<ConfigAttribute> it = attributes.iterator();
		int result = ACCESS_ABSTAIN;
		while (it.hasNext()) {
			// 资源的权限
			ConfigAttribute resourceAttr = it.next();
			String resourceRole = resourceAttr.getAttribute();
            if (resourceRole == null) {
				continue;
			}
            if (this.supports(resourceAttr)) {
            	result = ACCESS_DENIED;
            	// 用户的权限
            	for (GrantedAuthority userAuth : authentication.getAuthorities()) {
            		logger.info("[资源角色==用户角色] ？ {} == {}", resourceRole.trim(), userAuth.getAuthority().trim());
            		if (resourceRole.trim().equals(userAuth.getAuthority().trim())) {
            			return ACCESS_GRANTED;
            		}
            	}
			}
		}
		return result;
	}
}
