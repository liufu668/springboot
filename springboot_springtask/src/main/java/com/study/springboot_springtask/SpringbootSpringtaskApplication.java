package com.study.springboot_springtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 开启基于注解的定时任务,发现注解@Scheduled的任务并由后台执行
public class SpringbootSpringtaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSpringtaskApplication.class, args);
	}

}
