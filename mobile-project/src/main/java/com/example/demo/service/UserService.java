package com.example.demo.service;

public interface UserService {

	boolean authenticate(String username, String password);
	
	void loginUser(String username);
	
	void logoutCurrentUser();
}
