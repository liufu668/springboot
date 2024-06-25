package com.study.springboot_interceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
public class InterceptorController {

    @RequestMapping("/index")
    public String index(){
        return "欢迎访问XXX网站";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        request.setAttribute("value","登录前在此保存了一些属性值");//向请求中插入一个属性值
        return "请先登录";
    }
}
