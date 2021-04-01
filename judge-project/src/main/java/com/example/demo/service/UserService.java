package com.example.demo.service;

import com.example.demo.model.service.UserServiceModel;

public interface UserService {

	public void registerUser(UserServiceModel userServiceModel);

	public UserServiceModel findUserByUserNameAndPassword(String username, String password);

	public void login(UserServiceModel user);


}
