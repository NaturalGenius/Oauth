package com.zhuliang.oauth.event;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.util.DateUtil;

/**
 * 监听登录成功事件
 * 
 * @author zhuliang
 * @date 2019年12月21日
 */
@Component
public class LoginSucessEvent {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    @Async
    public void listenEvent(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
         String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }else if (principal instanceof OAuth2User) {
            userName = ((OAuth2User) principal).getName();
        }else {
            userName = principal.toString();
        }
        logger.info("用户:{} 在{} 登录系统", userName, DateUtil.format(new Date()));
    }
}
