package com.zhuliang.oauth.filter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.manage.CustomAccessDecisionManager;

/**
 * 权限拦截管理器 检测用户行为
 * 
 * @author zhuliang
 * @date 2019年12月3日
 */
@Component
public class CustomFilterSecurityInterceptor extends FilterSecurityInterceptor{

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;

    @Override
    public Class<?> getSecureObjectClass() {
        return  FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }

    @PostConstruct
    public void initSetManager() {
        super.setAccessDecisionManager(customAccessDecisionManager);
        super.setPublishAuthorizationSuccess(true);
    }

}
