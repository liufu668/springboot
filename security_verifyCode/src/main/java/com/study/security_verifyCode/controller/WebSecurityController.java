package com.study.security_verifyCode.controller;

import com.study.security_verifyCode.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @GetMapping("/verifyCode")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 创建验证码工具类实例
        VerifyCode vc = new VerifyCode();
        // 生成验证码图片
        BufferedImage image = vc.getImage();
        // 获取验证码文本
        String text = vc.getText();
        // 获取当前HTTP会话对象
        HttpSession session = req.getSession();
        // 将验证码文本存储到会话中，用于后续验证
        session.setAttribute("verify_code",text);
        // 将验证码图片写入HTTP响应流,返回给客户端
        VerifyCode.output(image,resp.getOutputStream());
    }
}
