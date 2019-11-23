package com.zhuliang.oauth.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * <p>
 * 
 * </p>
 *
 * @author zhuliang
 * @since 2017-12-20
 */
@TableName("role_permision")
public class RolePermision implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer roleId;
	private Integer permissionId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public static final String ID = "id";

	public static final String ROLE_ID = "role_id";

	public static final String PERMISSION_ID = "permission_id";

	@Override
	public String toString() {
		return "RolePermision{" +
			"id=" + id +
			", roleId=" + roleId +
			", permissionId=" + permissionId +
			"}";
	}
}
