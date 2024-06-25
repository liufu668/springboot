package com.study.springboot_exception.exception;

import com.study.springboot_exception.event.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

/**
 * 全局异常处理类
 */
@Slf4j
@RestControllerAdvice//标记该类为全局异常处理类
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MyBusinessException.class)//处理特定异常
    public Result handleBizException(MyBusinessException ex){
        Result<Object> result=new Result<>();
        result.setCode(ex.getCode());
        result.setMessage(ex.getMessage());
        return result;
    }

    /**
     * 参数校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        StringJoiner sj=new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        Result<Object> result=new Result<>();
        result.setCode(505);
        result.setMessage(sj.toString());
        return result;
    }

    /**
     * Controller参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
        Result<Object> result=new Result<>();
        result.setCode(506);
        result.setMessage(ex.getMessage());
        return result;
    }

    /**
     * 其他未知异常(拦截的是全局最底层异常,兜底)
     */
    @ExceptionHandler(value=Exception.class)
    public Result handleException(Exception ex){
        Result<Object> result=new Result<>();
        result.setCode(507);
        result.setMessage("服务器内部错误");
        return result;
    }
}
