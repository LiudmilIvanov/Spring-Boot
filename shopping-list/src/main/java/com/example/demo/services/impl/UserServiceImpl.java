package com.example.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dtos.UserLoginBindingModel;
import com.example.demo.model.entities.User;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.model.services.UserRegisterServiceModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final CurrentUser currentUser;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
			PasswordEncoder passwordEncoder, CurrentUser currentUser) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.currentUser = currentUser;
	}





	@Override
	public void save(UserRegisterServiceModel userModel) {
		User user = modelMapper.map(userModel, User.class);
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		
		userRepository.save(user);
	}





	@Override
	public boolean authenticate(String name, String password) {
		Optional<User> userOpt = userRepository.findByUsername(name);
		
		if (userOpt.isEmpty()) {
			return false;
		} else {
			return	passwordEncoder.matches(password, userOpt.get().getPassword());
		}
	}





	@Override
	public void login(UserLoginBindingModel userModel) {
		currentUser.setUsername(userModel.getUsername());
		currentUser.setRole(RoleTypeEnum.USER);
	}





	@Override
	public boolean isLogged() {
		return currentUser.getUsername() != null;
	}





	@Override
	public void logout() {
		currentUser.setUsername(null);
		currentUser.setRole(null);
		
	}

}
