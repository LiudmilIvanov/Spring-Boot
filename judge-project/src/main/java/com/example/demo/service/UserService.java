package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.RoleName;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.model.view.UserProfileViewModel;

public interface UserService {

	public void registerUser(UserServiceModel userServiceModel);

	public UserServiceModel findUserByUserNameAndPassword(String username, String password);

	public void login(UserServiceModel user);

	public void logout();

	public List<String> findAllUsernames();

	public void changeRole(String username, RoleName roleName);

	public User findById(Long id);

	public UserProfileViewModel findProfileById(Long id);

	public Long findUserCount();


}
