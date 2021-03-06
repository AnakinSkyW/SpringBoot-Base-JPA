package com.anakin.generator.exception;

import com.anakin.common.tool.exception.CommonException;
import com.anakin.common.tool.result.ApiResult;
import com.anakin.common.tool.enums.CodeMsg;
import com.anakin.common.tool.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description: 全局异常捕获
 * @author: anakin
 * @date 2019/4/24 10:07
 */
@Order(1)
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 异常捕获
     * @param exception 异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult exceptionHandle(Exception exception) {
        log.error("系统异常", exception);
        return ResultUtil.response(CodeMsg.FAILED);
    }

    /**
     * 通用异常捕获
     * @param commonException 通用异常
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ApiResult commonExceptionHandle(CommonException commonException) {
        log.error("通用异常:" + commonException.getMessage());
        return ResultUtil.response(commonException.getCode(), commonException.getMessage());
    }

    /**
     * 参数校验异常绑定
     * @param bindException 绑定异常
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ApiResult bindExceptionHandle(BindException bindException) {
        StringBuilder msgBuilder = new StringBuilder();
        bindException.getFieldErrors().forEach(error -> msgBuilder.append(error.getField()).append(":")
                .append(error.getDefaultMessage()).append("/"));
        msgBuilder.deleteCharAt(msgBuilder.lastIndexOf("/"));
        String errorMsg = msgBuilder.toString();
        log.error("参数异常:" + errorMsg);
        return ResultUtil.response(CodeMsg.VALIDATE_PARAMETER.getCode(), errorMsg);
    }

    /**
     * 参数校验异常绑定
     * @param ex 绑定异常
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResult argumentNotValid(MethodArgumentNotValidException ex) {
        StringBuilder msgBuilder = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> msgBuilder.append(error.getField()).append(":")
                .append(error.getDefaultMessage()).append("/"));
        msgBuilder.deleteCharAt(msgBuilder.lastIndexOf("/"));
        String errorMsg = msgBuilder.toString();
        log.error("参数异常:" + errorMsg);
        return ResultUtil.response(CodeMsg.VALIDATE_PARAMETER.getCode(), errorMsg);
    }

//    /**
//     * 权限不足
//     * @param ex 绑定异常
//     * @return
//     */
//    @ExceptionHandler(value = AccessDeniedException.class)
//    @ResponseBody
//    public ApiResult accessDeniedException(AccessDeniedException ex) {
//        log.error("权限不足:" + ex.getMessage());
//        return ResultUtil.response(CodeMsg.FORBIDDEN);
//    }

    /**
     * 参数校验异常绑定
     * @param ex 绑定异常
     * @return
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ApiResult argumentException(RuntimeException ex) {
        log.error("IllegalArgumentException:" + ex.getMessage());
        return ResultUtil.response(CodeMsg.VALIDATE_PARAMETER.getCode(),ex.getMessage());
    }
}
