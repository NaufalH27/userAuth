package io.userauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.userauth.data.repositories.UserRepository;
import io.userauth.models.dto.user.UserCreationDTO;
import io.userauth.models.entities.UserEntity;
import io.userauth.service.UserService;

@SpringBootTest
class AppTests {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Test
	public void createUser_testValid(){
		UserEntity user = new UserEntity();
		user.setEmail("momoiBalap@gmail.com");
		user.setName("momoiResing");
		user.setPasswordHash("momoi123");
		userRepository.createUser(user);
	}

	@Test
	void aaaaaaaaaaaaa() {
		UserCreationDTO user = new UserCreationDTO();
			user.setName("momoiBalap");
			user.setEmail("momoiResing123@gmail.com");
			user.setPassword( "momoi123");
			user.setRepeatPassword("momoi23");
			userService.createUser(user);
		}
		

}
