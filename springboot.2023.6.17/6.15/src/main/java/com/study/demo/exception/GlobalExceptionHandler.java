package com.study.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {//全局异常处理

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exception(Exception e){
        log.info(e.toString());//打印日志信息
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        if(e instanceof BadCredentialsException){//匹配异常
            modelAndView.addObject("msg","密码错误");//密码错误
        }else if(e instanceof AccessDeniedException){
            modelAndView.addObject("msg",e.getMessage());//权限不足
        }else{
            modelAndView.addObject("msg","系统错误");
        }
        return modelAndView;
    }
}
