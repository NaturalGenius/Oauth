package com.zhuliang.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuliang.oauth.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
public interface UserService extends IService<User> {
	
	User selectByAccount(String account);
}