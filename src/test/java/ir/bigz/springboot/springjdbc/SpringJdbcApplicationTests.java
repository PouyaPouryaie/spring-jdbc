package ir.bigz.springboot.springjdbc;

import ir.bigz.springboot.springjdbc.user.UserRepository;
import ir.bigz.springboot.springjdbc.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.List;

@SpringBootTest(classes = SpringJdbcApplication.class)
class SpringJdbcApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}


	@Test
	public void findAllUsers()  {
		List<User> users = userRepository.findAll();
		AssertionErrors.assertNotNull("find user ", users);
		Assertions.assertTrue(!users.isEmpty());

	}

	@Test
	public void findUserById()  {
		User user = userRepository.findUserById(1);
		Assertions.assertNotNull(user);
	}

	@Test
	public void createUser() {
		User user = new User(0,"atena", "atena@gmail.com");
		User savedUser = userRepository.create(user);
		User newUser = userRepository.findUserById(savedUser.getId());
		Assertions.assertEquals("atena", newUser.getName());
		Assertions.assertEquals("atena@gmail.com", newUser.getEmail());
	}
}
