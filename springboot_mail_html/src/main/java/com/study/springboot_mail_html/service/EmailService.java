package com.study.springboot_mail_html.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public String sendHtmlMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //工具类MimeMessageHelper用于配置邮件的属性
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("3328518414@qq.com");
        helper.setTo("3328518414@qq.com");
        helper.setSubject("JavaMailSender发送邮件测试");

        //设置模板中的变量
        Context context = new Context();
        context.setVariable("username","融创软通");
        context.setVariable("num","001");
        context.setVariable("salary","￥100000");
        String value = templateEngine.process("template.html",context);//将数据模型注入到模板中，并生成最终的HTML
        helper.setText(value,true);//true表示发送HTML格式的邮件。
        javaMailSender.send(mimeMessage);
        return "HTML格式的邮件发送成功!";
    }
}
