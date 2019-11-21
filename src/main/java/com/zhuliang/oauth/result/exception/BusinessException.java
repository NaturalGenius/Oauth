package com.zhuliang.oauth.result.exception;

import com.zhuliang.oauth.result.enums.GlobalResultCode;
import com.zhuliang.oauth.result.enums.ResultCode;

/**
 * 业务异常类
 *@author lizhen
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 194906846739586856L;

	/** 异常状态码 */
	private Integer code;
	/** 异常提示信息 */
	private  String message;
	
	public BusinessException() {
	   this(GlobalResultCode.ERROR);
	}
	public BusinessException(ResultCode resultCode) {
		this(resultCode, resultCode.message());
	}
	public BusinessException(String message) {
		this(GlobalResultCode.ERROR, message);
	}
	public BusinessException(ResultCode resultCode, String message) {
		this.code = resultCode.code();
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
