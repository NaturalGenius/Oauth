package com.zhuliang.oauth.evaluator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.zhuliang.oauth.security.UserDetail;

@Component
public class CustomPermissiomEvaluator implements PermissionEvaluator{

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (permission == null) {
            return true;
        }
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        if (CollectionUtils.isEmpty(authorities)) {
            return false;
        }
        for (GrantedAuthority grantedAuthority : authorities) {
            if (Objects.equals(grantedAuthority.getAuthority(), permission.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        return false;
    }

}
