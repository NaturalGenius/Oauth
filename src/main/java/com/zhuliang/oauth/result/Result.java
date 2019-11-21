package com.zhuliang.oauth.result;

import com.zhuliang.oauth.result.enums.ResultCode;
import com.zhuliang.oauth.result.exception.BusinessException;

/**
 * 响应结果类
 * @author zhuliang
 *
 * @Date 2018年9月12日上午11:06:10
 */
public class Result {

    /**
     * 响应吗
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    
    /**
     * 响应体
     */
    private Object data;

    public static Result newInstance() {
        return new Result();
    }
    public Result() {
    }
    public Result setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        return this;
    }
    public Result setBusinessException(BusinessException businessException) {
        this.code = businessException.getCode();
        this.message = businessException.getMessage();
        return this;
    }
    public Integer getCode() {
        return code;
        
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result  setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result  setData(Object data) {
        this.data = data;
        return this;
    }
}
