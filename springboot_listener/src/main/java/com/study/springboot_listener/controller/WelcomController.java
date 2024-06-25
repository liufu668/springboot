package com.study.springboot_listener.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

    @RequestMapping("/listenerIndex")
    public String index(){
        return "欢迎访问XXX网站";
    }
}
