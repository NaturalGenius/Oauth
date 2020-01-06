package com.zhuliang.oauth.event;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        logger.info("用户:{} 在{} 登录系统", userDetails.getUsername(), DateUtil.format(new Date()));
    }
}
