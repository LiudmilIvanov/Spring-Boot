package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.UserEntity;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.model.services.UserRegisterServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}



	@Override
	public void save(UserRegisterServiceModel userRegisterServiceModel) {
		UserEntity user = modelMapper.map(userRegisterServiceModel, UserEntity.class);
	
		
		user.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
		user.setRoles(List.of(roleService.findByName(RoleTypeEnum.ROLE_USER)));
		
		userRepository.save(user);
		
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		
		 Authentication authentication = new
			        UsernamePasswordAuthenticationToken(
			        userDetails,
			        user.getPassword(),
			        userDetails.getAuthorities()
			       
			    );
			    SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}



	@Override
	public Optional<UserEntity> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}



	@Override
	public UserEntity findByName(String name) {
		return userRepository.findByUsername(name).orElse(null);
	}



	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	

}
