package com.zhuliang.oauth.source.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import com.zhuliang.oauth.dto.PermissionDto;
import com.zhuliang.oauth.entity.Role;
import com.zhuliang.oauth.service.PermissionService;

/**
 * 权限资源管理
 * 为权限决断器提供支持
 * @author zhuliang
 * @date 2019年12月3日
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Logger logger = LoggerFactory.getLogger(CustomSecurityMetadataSource.class);
    private Map<RequestMatcher, Collection<ConfigAttribute>> permissionMap;
    private PermissionService permissionService;
    private FilterInvocationSecurityMetadataSource  superMetadataSource;


    public CustomSecurityMetadataSource(FilterInvocationSecurityMetadataSource expressionBasedFilterInvocationSecurityMetadataSource,PermissionService permissionService){
         this.superMetadataSource = expressionBasedFilterInvocationSecurityMetadataSource;
         this.permissionService = permissionService;
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        logger.info("[资源被访问：根据URL找到权限配置]: {}\n {}", object, permissionMap);

        if (permissionMap == null) {
            loadResourceDefine();
        }
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Collection<ConfigAttribute> attributes = permissionMap.get(new AntPathRequestMatcher(request.getServletPath(), request.getMethod()));
        if (!CollectionUtils.isEmpty(attributes)) {
            return attributes;
        }
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : permissionMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                logger.info("[找到的Key]: {}", entry.getKey());
                logger.info("[找到的Value]: {}", entry.getValue());
                if (entry.getValue().size() > 0) {
                    return entry.getValue();
                }
            }
        }
       //  返回代码定义的默认配置
       return superMetadataSource.getAttributes(object);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 在Web服务器启动时，缓存系统中的所有权限映射。<br>
     * 被{@link PostConstruct}修饰的方法会在服务器加载Servlet的时候运行(构造器之后,init()之前) <br/>
     */
    @PostConstruct
    private void loadResourceDefine() {
        permissionMap = new LinkedHashMap<>();
        // 需要鉴权的url资源，@needAuth标志
        List<PermissionDto> permissionList = permissionService.getAllPermissions();
        for (PermissionDto permission : permissionList) {
            String url = permission.getUrl();
            String method = permission.getMethod();
            List<Role> roles = permission.getRoles();
            logger.info("{} - {}", url, method);
            AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(url, method);

            Collection<ConfigAttribute> attributes = new ArrayList<>();
            if (!CollectionUtils.isEmpty(roles)) {
                for (Role role : roles) {
                    attributes.add(new SecurityConfig(role.getName()));
                }
            }
            // 占位符，需要权限才能访问的资源 都需要添加一个占位符，保证value不是空的
            attributes.add(new SecurityConfig("@needAuth"));
            permissionMap.put(requestMatcher, attributes);
        }
        logger.info("[全局权限映射集合初始化]: {}", permissionMap.toString());
    }
}
