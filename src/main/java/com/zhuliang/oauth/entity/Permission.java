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
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private String url;
	private Integer menu;


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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String URL = "url";

	public Integer getMenu() {
		return menu;
	}

	public void setMenu(Integer menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Permission{" +
			"id=" + id +
			", name=" + name +
			", url=" + url +
			"}";
	}
}
