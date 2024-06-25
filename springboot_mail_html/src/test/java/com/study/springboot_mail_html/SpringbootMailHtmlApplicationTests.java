package com.study.springboot_mail_html;

import com.study.springboot_mail_html.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;


@SpringBootTest
class SpringbootMailHtmlApplicationTests {

    @Autowired
    EmailService emailService;

    @Test
    public void test() throws MessagingException {
        emailService.sendHtmlMail();
    }
}
