package com.zhuliang.oauth.result.enums;

/**
 * 统一封装响应吗和响应消息
 * 
 * @author zhuliang
 *
 * @Date 2018年6月26日下午1:35:32
 */
public interface ResultCode {

	/**
	 * 响应吗
	 * @return
	 */
	Integer code();

	/**
	 * 提示信息
	 * @return
	 */
	String message();
}
