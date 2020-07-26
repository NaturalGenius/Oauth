package com.zhuliang.oauth;

import java.util.Date;

import com.alibaba.fastjson.util.TypeUtils;
import com.zhuliang.oauth.util.DateUtil;

public class Test {

	public static void main(String[] args) {
		try {
             Date castToJavaBean = TypeUtils.castToJavaBean("2020-02-02ss", Date.class);
            System.out.println(DateUtil.format(castToJavaBean));
		} catch (Exception e) {
			System.out.println("qqqq");
          
        }
	}
}
