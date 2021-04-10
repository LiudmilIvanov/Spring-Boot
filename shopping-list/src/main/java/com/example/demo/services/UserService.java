package com.example.demo.services;

import com.example.demo.model.dtos.UserLoginBindingModel;
import com.example.demo.model.dtos.UserRegisterBindingModel;
import com.example.demo.model.services.UserRegisterServiceModel;

public interface UserService {

	public void save(UserRegisterServiceModel userModel);
	
	boolean authenticate(String name, String password);

	public void login(UserLoginBindingModel userModel);
	
	public boolean isLogged();
}
