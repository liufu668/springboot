package com.study.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 1.将configure(HttpSecurity http)方法中设置的不同的URL映射到不同的页面
 * 2.方法返回的是视图名称,需要视图解析器将视图名称解析成实际的HTML文件
 * 然后访问url就可以跳转到HTML页面了,否则返回的只是一个字符串
 * 3.在application.properties配置文件中配置视图解析器,springboot已经默认配置好了,你不用写了
 */
@Controller
public class WebSecurityController {

    /**
     * 登录后跳转到home.html页面
     */
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login(){
        return "login";//login.html
    }

    /**
     * 当访问/resource时,会重定向到/login,登录后才可以访问受保护的页面resource.html
     */
    @GetMapping("/resource")
    public String resource(){
        return "resource";//resource.html
    }
}
