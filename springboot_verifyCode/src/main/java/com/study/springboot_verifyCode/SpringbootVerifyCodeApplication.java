package com.study.springboot_verifyCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity//启动Spring Security功能
public class SpringbootVerifyCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVerifyCodeApplication.class, args);
	}

}
