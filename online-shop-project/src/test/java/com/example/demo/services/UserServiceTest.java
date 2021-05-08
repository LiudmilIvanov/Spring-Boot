package com.example.demo.services;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.entities.UserEntity;
import com.example.demo.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void testFindByName() {
		UserEntity user = new UserEntity();
		user.setId(2);
		user.setUsername("pesho");
		user.setPassword("12314");
		
		doReturn(Optional.of(user)).when(userRepository).findByUsername("pesho");
		
		Optional<UserEntity> returnUser = userService.findByUsername("pesho");
		
		Assertions.assertTrue(returnUser.isPresent());
	}
	
	void testUpdateUserInformation() {
		
	}
}
