package com.study.springboot_slf4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final static Logger logger= LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/log")
    public String testLog(){
        logger.debug("===========测试日志debug级别打印===========");
        logger.info("===========测试日志info级别打印===========");
        logger.error("===========测试日志error级别打印===========");
        logger.warn("===========测试日志warn级别打印===========");

        //可使用占位符打印出一些参数信息
        String str1="Spring Boot";
        String str2="SLF4j";
        logger.info("当前案例使用的是{}整合{}案例!",str1,str2);
        return "日志记录成功";

    }
}
