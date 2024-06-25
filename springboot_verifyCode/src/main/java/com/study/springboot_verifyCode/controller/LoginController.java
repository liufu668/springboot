package com.study.springboot_verifyCode.controller;

import com.study.springboot_verifyCode.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @GetMapping("/doLogin")
    public String login(){
        return "login";//login.html
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
