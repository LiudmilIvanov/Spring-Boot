package com.example.demo.security;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.services.UserService;


@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserService userService;
	
	
	@Autowired
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntityOpt = userService.findByUsername(username);
		
		return userEntityOpt
				.map(this::map)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
		
	}

	private UserDetails map(UserEntity userEntity) {
		
		return new User(userEntity.getUsername(), userEntity.getPassword(),
				userEntity.getRoles().stream().map(this::map).collect(Collectors.toList())
				);
	}
	
	private GrantedAuthority map(RoleEntity roleEntity) {
		
		return new SimpleGrantedAuthority(roleEntity.getName().name());
	}
}
