package com.study.jwt;

import com.study.jwt.entity.User;
import com.study.jwt.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 创建两个用户zhang和admin
	 */
	@Test
	void saveUser(){
		User user = new User();
		user.setUsername("zhang");
		//使用BCryptPasswordEncoder对提交的密码加密
		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user.setMobile("18323415151");
		user.setRoles("ROLE_USER");
		userMapper.saveUser(user);

		user=new User();
		user.setUsername("admin");
		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user.setMobile("16813512343");
		user.setRoles("ROLE_USER,ROLE_ADMIN");
		userMapper.saveUser(user);
	}


	@Test
	void contextLoads() {
	}

}
