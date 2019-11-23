package com.zhuliang.oauth.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuliang.oauth.entity.UserRole;
import com.zhuliang.oauth.mapper.UserRoleMapper;
import com.zhuliang.oauth.service.UserRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
	
}
