package com.springboot_JavaMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 可以在启动类中创建SpringEmailService类的实例并调用其sendSimpleMail()方法
 * 在这个示例中，我们注入了SpringEmailService类的实例，
 * 并在run()方法中调用了sendSimpleMail()方法。
 * 由于CommandLineRunner接口的存在，run()方法将在启动应用程序时自动执行。
 *
 */
@SpringBootApplication
public class SpringbootJavaMailApplication implements CommandLineRunner {

	@Autowired
	private SpringEmailService springEmailService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJavaMailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		springEmailService.sendSimpleMail();
	}
}
