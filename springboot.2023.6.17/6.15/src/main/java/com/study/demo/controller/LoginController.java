package com.study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {//用于用户登录

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
