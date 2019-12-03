package com.zhuliang.oauth.manage;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    private static Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("[资源权限]: {}", configAttributes);
        logger.info("[用户权限]: {}", authentication.getAuthorities());
        
        Iterator<ConfigAttribute> it = configAttributes.iterator();
        while (it.hasNext()) {
            // 资源的权限
            ConfigAttribute resourceAttr = it.next();
            String resourceRole = ((SecurityConfig) resourceAttr).getAttribute();

            // 用户的权限
            for (GrantedAuthority userAuth : authentication.getAuthorities()) {
                logger.info("[资源角色==用户角色] ？ {} == {}", resourceRole.trim(), userAuth.getAuthority().trim());
                if (resourceRole.trim().equals(userAuth.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
