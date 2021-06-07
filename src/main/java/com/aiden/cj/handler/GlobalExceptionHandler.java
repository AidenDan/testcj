package com.aiden.cj.handler;

import com.aiden.cj.config.BaseResponse;
import com.aiden.cj.exception.BaseException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice("com.aiden.cj")
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        response.setStatus(500);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse beanBindValidExceptionHandler(HttpServletResponse response, BindException ex) {
        response.setStatus(500);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return new BaseResponse(500, fieldError.getField()+"-"+fieldError.getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse beanValidExceptionHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
        response.setStatus(500);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return new BaseResponse(500, fieldError.getField()+"-"+fieldError.getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse illegalArgumentExceptionHandler(HttpServletResponse response, IllegalArgumentException ex) {
        response.setStatus(500);
        return new BaseResponse(500, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        String message = "全局异常错误："+ex.getMessage();
        return new BaseResponse(500, message);
    }
}
