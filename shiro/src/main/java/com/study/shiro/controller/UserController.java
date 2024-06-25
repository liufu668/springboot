package com.study.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    //主页面
    @RequestMapping({"/index","/"})
    public String index(Model model){
        model.addAttribute("msg","hello shiro!");
        return "index";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String username,String password,Model model){
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //执行登录的方法,只要没有异常就代表登录成功
        try {
            currentUser.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg","用户名不存在!");
            return "login";
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg","密码错误!");
            return "login";
        }

    }

    //注销
    @RequestMapping("/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "index";
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String noAuth(){
        return "未经授权不能访问此页面";
    }

    //获取指定授权后,可访问该页面
    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    //获取指定授权后,可访问该页面
    @RequestMapping("/user/delete")
    public String delete(){
        return "/user/delete";
    }
}
