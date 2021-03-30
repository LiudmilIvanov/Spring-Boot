package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.UserRoleEnum;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final CurrentUser currentUser;
	
	
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder,
			UserRepository userRepository, CurrentUser currentUser) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.currentUser = currentUser;
	}



	@Override
	public boolean authenticate(String username, String password) {
		Optional<User> userEntityOptional = userRepository.findByUsername(username);;
		
		if (userEntityOptional.isEmpty()) {
			return false;
		} else {
			return passwordEncoder.matches(password, userEntityOptional.get().getPassword());
		}
	}



	@Override
	public void loginUser(String username) {
		User user = userRepository.findByUsername(username).orElseThrow();
		
		List<UserRoleEnum> userRoles = user
				.getUserRoles()
				.stream()
				.map(ur -> ur.getRole())
				.collect(Collectors.toList());
		
		currentUser
		.setAnnonymous(false)
		.setName(user.getUsername());

		currentUser.setUserRoles(userRoles);
		System.out.println();
	}



	@Override
	public void logoutCurrentUser() {
		currentUser.setAnnonymous(true);
		
		
	}

	
}
