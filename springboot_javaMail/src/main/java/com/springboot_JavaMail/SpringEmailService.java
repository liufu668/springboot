package com.springboot_JavaMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SpringEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private String subject = "JavaMailSender发送邮件测试";

    private String text = "SpringBoot使用JavaMailSender发送邮件";

    private String to = "3328518414@qq.com";

    private String from = to;

    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject); // 主题
        message.setText(text); // 内容
        message.setTo(to); // 收件人
        message.setFrom(from); // 发件人
        javaMailSender.send(message); // 发送邮件
        System.out.println("邮件发送成功!");
    }
}
