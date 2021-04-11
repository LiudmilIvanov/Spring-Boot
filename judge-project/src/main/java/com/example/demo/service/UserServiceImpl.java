package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.RoleName;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.model.view.UserProfileViewModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;

@Service
public class UserServiceImpl implements UserService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RoleService roleService;
	private final CurrentUser currentUser;
	
	
	@Autowired
	public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService,
			CurrentUser currentUser) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.currentUser = currentUser;
	}



	@Override
	public void registerUser(UserServiceModel userServiceModel) {
		User user = modelMapper.map(userServiceModel, User.class);
		user.setRole(roleService.findRole(RoleName.ADMIN));

		userRepository.save(user);
	}



	@Override
	public UserServiceModel findUserByUserNameAndPassword(String username, String password) {
		
		return userRepository
				.findByUsernameAndPassword(username, password)
				.map(user -> modelMapper.map(user, UserServiceModel.class))
				.orElse(null);
	}



	@Override
	public void login(UserServiceModel user) {
		currentUser
		.setId(user.getId())
		.setUsername(user.getUsername())
		.setRole(user.getRole().getName());
		
	}



	@Override
	public void logout() {
		currentUser
			.setId(null)
			.setRole(null)
			.setUsername(null);
	}



	@Override
	public List<String> findAllUsernames() {

		return userRepository.findAllUsernames();
	}



	@Override
	public void changeRole(String username, RoleName roleName) {
		User user = userRepository.findByUsername(username).orElse(null);
		
			if (user.getRole().getName() != roleName) {
				user.setRole(roleService.findRole(roleName));
					
				userRepository.save(user);
		}
	}



	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}



	@Override
	public UserProfileViewModel findProfileById(Long id) {
		User user = userRepository.findById(id).orElse(null);

		UserProfileViewModel userProfileViewModel = modelMapper.map(user, UserProfileViewModel.class);
		
		userProfileViewModel.setHomeworkSet(user
				.getHomework()
				.stream()
				.map(homework -> homework.getExercise().getName())
				.collect(Collectors.toSet())
				);
				

		return userProfileViewModel;
	}

}
