package com.example.demo.services;

import java.util.Optional;

import com.example.demo.model.entities.UserEntity;
import com.example.demo.model.services.UserRegisterServiceModel;

public interface UserService {

	void save(UserRegisterServiceModel userRegisterServiceModel);

	Optional<UserEntity> findByUsername(String username);
	
	UserEntity findByName(String name);
}
