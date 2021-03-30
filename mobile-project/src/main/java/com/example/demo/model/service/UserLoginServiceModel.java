package com.example.demo.model.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginServiceModel {

	private String username;
	private String password;

	public UserLoginServiceModel() {

	}

	@NotNull
	@Size(min = 2)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	@Size(min = 3)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
