package com.example.demo.services;

import java.util.Optional;

import com.example.demo.model.binding.UserUpdateBindingModel;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.model.services.UserRegisterServiceModel;
import com.example.demo.model.services.UserServiceModel;

public interface UserService {

	void save(UserRegisterServiceModel userRegisterServiceModel);

	Optional<UserEntity> findByUsername(String username);
	
	UserServiceModel findByName(String name);
	
	boolean existsByUsername(String username);

	void updateUserInformation(UserUpdateBindingModel userUpdateBindingModel, String name);
	
}
