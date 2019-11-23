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
@TableName("role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;
	private String desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static final String ID = "id";

	public static final String NAME = "name";
	public static final String DESC = "desc";

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}

}
