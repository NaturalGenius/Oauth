package com.zhuliang.oauth.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.event.AuthorizedEvent;

import com.alibaba.fastjson.JSON;

public class AuthorizedSuccessEvent {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
  
    @EventListener
    @Async
    public void listenEvent(AuthorizedEvent event) {
        logger.info(JSON.toJSONString(event));
    }
}
