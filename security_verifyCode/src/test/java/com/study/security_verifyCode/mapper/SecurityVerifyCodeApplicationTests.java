package com.study.security_verifyCode.mapper;

import com.study.security_verifyCode.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 单元测试类的包结构与UserMapper接口一致,
 * 前者为test/java/com/study/spring_security/mapper
 * 后者为main/java/com/study/spring_security/mapper
 */
@SpringBootTest
class SecurityVerifyCodeApplicationTests {

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

}
