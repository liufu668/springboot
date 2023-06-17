package com.study.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@Controller //用于接受用户请求并调用Service层返回数据给前端页面
@Slf4j //为类提供一个日志对象
public class UserController {

    @ResponseBody//设置当前控制器方法响应内容为当前返回值
    @RequestMapping("/hello")//设置当前控制器方法请求访问路径
    public String hello(){
        return "hello";
    }

    @GetMapping("/loginPage")//设置当前控制器方法请求访问路径，GetMapping对应GET请求动作
    public String login(){
        return "login";
    }

    @GetMapping("/exception")
    public String error(HttpServletRequest request) throws AuthenticationException {
        AuthenticationException exception=(AuthenticationException) WebUtils.getSessionAttribute(request,"SPRING_SECURITY_LAST_EXCEPTION");
            if (exception != null) {
                throw exception;
            }
            return "redirect:/loginPage";
    }

    @GetMapping({"/index","/"})
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/role/teacher")
    @Secured({"ROLE_teacher","ROLE_admin"})//拥有特定角色的用户可以访问 
    public String teacher(){
        return "模拟获取老师数据";
    }

    @GetMapping("/role/admin")
    @Secured({"ROLE_admin"})
    public String admin(){
        return "模拟获取管理员数据";
    }

    @GetMapping("/role/student")
    @Secured({"ROLE_student","ROLE_admin"})
    public String student(){
        return "模拟获取学生数据";
    }

}
