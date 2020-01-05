package com.zhuliang.oauth.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuliang
 * @since 2019-12-03
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 权限控制器
     */
    private String control;

    /**
     * 所属系统
     */
    @TableField("`system`")
    private String system;

    /**
     * 菜单 1 菜单 2 按钮
     */
    private Integer menu;

    /**
     * 路径
     */
    private String url;

    /**
     * 描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态 1正常 2 删除
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 请求方法
     */
    private String method;


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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PID = "pid";

    public static final String CONTROL = "control";

    public static final String SYSTEM = "system";

    public static final String MENU = "menu";

    public static final String URL = "url";

    public static final String DESC = "desc";

    public static final String OPERATOR_ID = "operator_id";

    public static final String OPERATOR = "operator";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String STATUS = "status";

    public static final String METHOD = "method";

    @Override
    public String toString() {
        return "Permission{" +
        "id=" + id +
        ", name=" + name +
        ", pid=" + pid +
        ", control=" + control +
        ", system=" + system +
        ", menu=" + menu +
        ", url=" + url +
        ", desc=" + desc +
        ", operatorId=" + operatorId +
        ", operator=" + operator +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", method=" + method +
        "}";
    }
}
