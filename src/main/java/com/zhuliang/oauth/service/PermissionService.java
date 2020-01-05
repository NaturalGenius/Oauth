package com.zhuliang.oauth.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuliang.oauth.dto.PermissionDto;
import com.zhuliang.oauth.entity.Permission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
public interface PermissionService extends IService<Permission> {
	
    /**
     * 获取所有的权限
     * @return
     */
    List<PermissionDto>  getAllPermissions();
}
