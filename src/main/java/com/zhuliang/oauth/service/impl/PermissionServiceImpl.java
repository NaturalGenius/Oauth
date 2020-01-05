package com.zhuliang.oauth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuliang.oauth.dto.PermissionDto;
import com.zhuliang.oauth.entity.Permission;
import com.zhuliang.oauth.entity.Role;
import com.zhuliang.oauth.entity.RolePermission;
import com.zhuliang.oauth.mapper.PermissionMapper;
import com.zhuliang.oauth.service.PermissionService;
import com.zhuliang.oauth.service.RolePermisionService;
import com.zhuliang.oauth.service.RoleService;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private RolePermisionService rolePermisionService;
    @Override
    public List<PermissionDto> getAllPermissions() {
        List<PermissionDto> permissionDtos = new ArrayList<>();
        Map<Integer, Role> roleMap = roleService.list().stream().collect(Collectors.toMap(Role::getId, Function.identity()));
        Map<Integer, List<RolePermission>> rPMap = rolePermisionService.list().stream().collect(Collectors.groupingBy(RolePermission::getPermissionId));
        List<Permission> permissions = list();
        for (Permission permission : permissions) {
            PermissionDto permissionDto = new PermissionDto();
            BeanUtils.copyProperties(permission, permissionDto);
            List<Role> roles = new ArrayList<>();
            List<RolePermission> rolePermisions = rPMap.get(permission.getId());
            for (RolePermission rolePermision : rolePermisions) {
                roles.add(roleMap.get(rolePermision.getRoleId()));
            }
            permissionDto.setRoles(roles);
            permissionDtos.add(permissionDto);
        }
        return permissionDtos;
    }
	
}
