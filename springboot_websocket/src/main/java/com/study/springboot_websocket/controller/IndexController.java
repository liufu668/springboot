package com.study.springboot_websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "socket";//当用户访问"/index"地址时跳转到socket.html页面
    }
}
