package com.zhuliang.oauth.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuliang
 * @since 2019-12-03
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 1 男 2 女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话号码
     */
    private String telphone;

    /**
     * 有限
     */
    private String email;

    /**
     * 操作人ID
     */
    private Integer operatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String rmark;

    /**
     * 状态 1正常 2 删除
     */
    private Integer status;

    /**
     * 昵称
     */
    private String nickName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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

    public String getRmark() {
        return rmark;
    }

    public void setRmark(String rmark) {
        this.rmark = rmark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static final String ID = "id";

    public static final String USER_NAME = "user_name";

    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String SEX = "sex";

    public static final String AGE = "age";

    public static final String TELPHONE = "telphone";

    public static final String EMAIL = "email";

    public static final String OPERATOR_ID = "operator_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String RMARK = "rmark";

    public static final String STATUS = "status";

    public static final String NICK_NAME = "nick_name";

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", userName=" + userName +
        ", account=" + account +
        ", password=" + password +
        ", sex=" + sex +
        ", age=" + age +
        ", telphone=" + telphone +
        ", email=" + email +
        ", operatorId=" + operatorId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", rmark=" + rmark +
        ", status=" + status +
        ", nickName=" + nickName +
        "}";
    }
}
