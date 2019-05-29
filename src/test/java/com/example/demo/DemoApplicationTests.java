package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private UserService service;

	@Test
	public void creatUser() {
		User u=new User();
		u.setUserName("zhouxu").setPassword("123456").setAuthorityName("Admin");
		service.createUser(u);
		assertNotNull(u);
		assertEquals(u.getUserName(),"zhouxu");
		assertEquals(u.getPassword(),"123456");
		assertEquals(u.getAuthorityName(),"Admin");
	}
}
