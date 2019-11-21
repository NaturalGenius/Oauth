package com.zhuliang.oauth.result.handler;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.annotation.ResultHandleNo;
import com.zhuliang.oauth.result.enums.GlobalResultCode;
import com.zhuliang.oauth.util.AnnotationUtil;



@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object>, InitializingBean{
    
    @Value("${responseResultHandler.basePackages:null}")
    private String basePackages;
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ResultHandleNo resultHandleNo = returnType.getMethod().getDeclaringClass().getAnnotation(ResultHandleNo.class);
        if (resultHandleNo == null) {
             resultHandleNo = returnType.getMethodAnnotation(ResultHandleNo.class);
        }
        return resultHandleNo == null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        return Result.newInstance().setResultCode(GlobalResultCode.SUCCESS).setData(body);    
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isNotEmpty(basePackages) && !"null".equals(basePackages)) {
            AnnotationUtil.modifyFiled(this, ControllerAdvice.class, "basePackages", basePackages.split(","));
        }
    }

}
