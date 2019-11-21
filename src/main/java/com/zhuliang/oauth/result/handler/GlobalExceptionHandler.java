package com.zhuliang.oauth.result.handler;



import org.springframework.http.HttpStatus;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.zhuliang.oauth.result.Result;
import com.zhuliang.oauth.result.enums.GlobalResultCode;
import com.zhuliang.oauth.result.exception.BusinessException;
import com.zhuliang.oauth.util.LogUtil;



/**
 * 统一异常处理器
 * @author Admin
 *
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler{

    private LogUtil logger = LogUtil.logger(GlobalExceptionHandler.class);
    /**
     * 违反数据库主键约束异常，例如主键长度约束、字符约束等
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e);
        return Result.newInstance().setResultCode(GlobalResultCode.PARAM_IS_BLANK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public  Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error(e);
        return Result.newInstance().setResultCode(GlobalResultCode.PARAM_NOT_COMPLETE)
                .setMessage(e.getMessage());
    }

    
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public  Result handleMissingServletRequestParameterException(HttpRequestMethodNotSupportedException e) {
        logger.error(e);
        return Result.newInstance().setResultCode(GlobalResultCode.METHOD_NOT_ALLOWED)
                .setMessage(e.getMessage());
    }
    /**
     * 请求地址不存在
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public  Result handleNoHandlerFoundException(NoHandlerFoundException e) {
        logger.error(e);
        return Result.newInstance().setResultCode(GlobalResultCode.URL_NOT_FOUND)
                .setMessage(e.getMessage());
    }
    /**
     * 服务接口调用失败
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RemoteAccessException.class)
    public  Result handleRemoteAccessException(RemoteAccessException e) {
        logger.error(e);
        return Result.newInstance().setResultCode(GlobalResultCode.REMOTING_ERROR)
                .setMessage(e.getMessage());
    }
    /**
        * 未知异常处理
        */
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error(e);
        return Result.newInstance().setResultCode(GlobalResultCode.ERROR);
    }
    
    /**
        * 业务异常处理
        */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        logger.error(e);
        return Result.newInstance().setBusinessException(e);
    }
}

