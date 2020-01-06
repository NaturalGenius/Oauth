package com.zhuliang.oauth.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 密码输入错误事件
 * @author zhuliang
 * @date 2019年12月23日
 */
@Component
public class BadCredentialsEvent {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @EventListener
    @Async
    public void listenEvent(AuthenticationFailureBadCredentialsEvent event) {
        //密码输入错误
        logger.info(JSON.toJSONString(event));
        
        //次数可以自定义错误密码输入多少次错误锁定用户
    }
}
