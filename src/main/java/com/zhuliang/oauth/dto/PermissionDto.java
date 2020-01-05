package com.zhuliang.oauth.dto;

import java.util.List;

import com.zhuliang.oauth.entity.Permission;
import com.zhuliang.oauth.entity.Role;

public class PermissionDto extends Permission{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private  List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
