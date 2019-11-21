package com.zhuliang.oauth.result.enums;



/**
 * API 统一返回状态码
 * @author zhuliang
 *
 * @date   2019年3月29日
 */
public enum GlobalResultCode implements ResultCode{
	
	/** 成功状态码 */
    SUCCESS(200, "成功"),
    ERROR(500, "系统繁忙，请稍后重试"),
	/** 全局异常状态码定义 */
    PARAM_IS_INVALID(100, "参数无效"),
    PARAM_IS_BLANK(101, "参数为空"),
    PARAM_TYPE_BIND_ERROR(102, "参数类型错误"),
    PARAM_NOT_COMPLETE(103, "参数缺失"),
    RESULE_DATA_NONE(104, "数据未找到"),
    DATA_IS_WRONG(105, "数据有误"),
    DATA_ALREADY_EXISTED(106, "数据已存在"),
    PERMISSION_NO_ACCESS(107, "无访问权限"),
    PARAM_CONSTRAINT_VIOLATION(108, "参数约束异常"),
    SYSTEM_INNER_ERROR(109, "系统繁忙，请稍后重试"),
    SYSTEM_NULL_POINTER(110,"参数异常"),
    SYSTEM_CLASS_NOTFOUND(111,"指定的类不存在"),
    SYSTEM_ARITHMETIC(112,"数学运算异常"),
    SYSTEM_INDEX_OUTOFBOUNDS(113,"数组下标越界"),
    SYSTEM_ILLEGAL_ARGUMENTS(114,"方法的参数错误"),
    SYSTEM_ILLEGA_ACCESS(115,"没有访问权限"),
    REMOTING_VERIFY_FAILED(116,"选择的机构不存在"),
    LOGIN_ERROR(117,"登录用户信息失效"),
    DATE_FORMAT_ERROR(118, "日期格式化错误"),
    REMOTING_ERROR(900,"调用远程服务异常"),
    METHOD_NOT_ALLOWED(405, "请求方式不支持"),
    URL_NOT_FOUND(404, "访问的页面不存在"),
    SHA256_VERIFY_ERROR(119, "参数验签失败");

    /**
	 * 全局异常状态码
	 * 状态码规则：状态码是6位长度的字符串。示例：1 01 100
	 * 1：应用标记（例如组织机构应用或者人员管理应用）
	 * 01：应用下的模块（例组织机构下的获取机构数据）
	 * 100：定义的业务异常
	 */
    
    
	/** 状态码 */
    private Integer code;
    /** 提示信息 */
    private String message;
    
    /** 构造器 */
    GlobalResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /** 获取状态码 */
    @Override
    public Integer code() {
        return this.code;
    }

    /** 获取提示信息 */
    @Override
    public String message() {
        return this.message;
    }
    
    /** 通过枚举属性名称获取提示信息 */
    public static String getMessage(String name) {
        for (GlobalResultCode item : GlobalResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    /** 更加枚举名称获取状态码 */
    public static Integer getCode(String name) {
        for (GlobalResultCode item : GlobalResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
