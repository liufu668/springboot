package com.study.springboot_exception.controller;

import com.study.springboot_exception.event.Result;
import com.study.springboot_exception.exception.MyBusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理系统中可能出现的两种异常:产品空指针异常和自定义异常
 */
@RestController
public class ExceptionController {

    /**
     * 系统内部错误
     */
    @GetMapping("/exception")
    public Result testException(){
        int i=1/0;//该语句会触发算数异常,进入全局异常处理类,所以不会执行下面的语句

        Result<Object> result=new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData("cc");
        return result;
    }
    /**
     * 自定义异常
     */
    @GetMapping("/myException")
    public Result testMyException(){
        throw new MyBusinessException(508,"自定义的异常");
    }
}
