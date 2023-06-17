package com.study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {//用于访问home页面

    @GetMapping("/home")//映射一个请求路径
    public String home(){
        return "home";
    }

    @GetMapping("/home2")//映射一个请求路径
    public String home2(){
        return "home2";
    }
}
