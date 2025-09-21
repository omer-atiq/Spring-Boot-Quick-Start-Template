package com.inovace.spring_aop;

import com.inovace.spring_aop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAopApplicationTests implements CommandLineRunner {

	@Autowired
	private UserService userService;


	@Test
	void contextLoads() {
	}

	@Override
	public void run(String... args) throws Exception {
		userService.getUserById(1);
		userService.createUser("Alice");
	}

}
