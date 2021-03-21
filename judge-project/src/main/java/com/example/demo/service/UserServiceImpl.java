package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.RoleName;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RoleService roleService;
	
	
	@Autowired
	public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.roleService = roleService;
	}



	@Override
	public void registerUser(UserServiceModel userServiceModel) {
		User user = modelMapper.map(userServiceModel, User.class);
		user.setRole(roleService.findRole(RoleName.USER));

		userRepository.save(user);
	}

}
