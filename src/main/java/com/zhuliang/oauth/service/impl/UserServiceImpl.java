package com.zhuliang.oauth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuliang.oauth.entity.User;
import com.zhuliang.oauth.mapper.UserMapper;
import com.zhuliang.oauth.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	@Transactional
	public User selectByAccount(String account) {
		return getOne(Wrappers.<User>query().eq(User.ACCOUNT, account));
	}
	
	@Override
	public boolean save(User entity) {
	    return super.save(entity);
	}
}
