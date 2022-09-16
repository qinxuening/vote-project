package com.vote.common.exception;

import com.vote.common.api.CommonResult;
import com.vote.common.api.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理类
 * @author qinxuening
 * @date 2022/9/12 23:11
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕获手动断言异常和基础异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handleBaseException(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    /**
     * 全局异常捕获数据校验异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleArgumentValidException(MethodArgumentNotValidException e) {
        return getCommonResult(e.getBindingResult());
    }

    /**
     * 全局异常捕获参数校验绑定异常处理，比如将@Validated写在方法参数，而不是类上
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult handleBindException(BindException e) {
        return getCommonResult(e.getBindingResult());
    }

    /**
     * 全局异常捕获资源，服务未找到
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public CommonResult handleBindException(NoHandlerFoundException e) {
        return CommonResult.failed(ResultCode.NOT_FOUND);
    }

    private CommonResult getCommonResult(BindingResult bindingResult) {
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }
}
