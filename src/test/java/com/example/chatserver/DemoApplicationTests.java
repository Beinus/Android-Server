package com.example.chatserver;

import com.example.chatserver.model.user.User;
import com.example.chatserver.model.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserService userDao;

	@Test
	void contextLoads() {
		User user = new User();
		user.setUserID("Android");
		user.setUserPW("Android1");
		userDao.save(user);
	}

	//	@Test
	void getAllEmployeesAndDeleteThem() {
		List<User> users = userDao.getAllUsers();
		users.forEach(user -> {
			userDao.delete(user);
		});
	}
}
