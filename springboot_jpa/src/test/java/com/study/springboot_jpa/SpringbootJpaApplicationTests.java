package com.study.springboot_jpa;

import com.study.springboot_jpa.model.User;
import com.study.springboot_jpa.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;


@Slf4j //自动生成日志
//运行测试时使用的测试运行器，指定为 SpringJUnit4ClassRunner.class，用于在测试前初始化 Spring 上下文。
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SpringbootJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void userTest(){
		User user = new User();
		user.setUserName("myCc");
		user.setAge(18);
		user.setPassword("123");

		//保存用户,如果主键存在,则执行更新,否则,执行插入操作
		userRepository.save(user);
		//根据名字查询用户
		User item = userRepository.findByUserName("myCc");
		System.out.println(item);
		//确保item不为空，如果 item为空，则断言失败，测试将会报错
		Assert.assertNotNull(item);
		//IP为1的用户是否存在
		Assert.assertEquals(true,userRepository.existsById(1L));

		//根据ID查询用户
		Optional<User> byId = userRepository.findById(1L);
		//确保ID为1的对象不为空,如果返回 true，那么断言成功
		Assert.assertEquals(true,byId.isPresent());
		//ID为2的用户不存在,期望是不存在,应该返回false
		Assert.assertEquals(false,userRepository.findById(2L).isPresent());
		//删除ID为1的用户
		userRepository.deleteById(1L);
		//判断ID为1的用户是否存在,期望是不存在,应该返回false
		Assert.assertEquals(false,userRepository.existsById(1L));
	}
}
