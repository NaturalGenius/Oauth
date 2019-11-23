package com.zhuliang.oauth.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuliang.oauth.entity.Role;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
public interface RoleService extends IService<Role> {
	
	List<Role> selectRolesByUserId(Integer userId);
}
