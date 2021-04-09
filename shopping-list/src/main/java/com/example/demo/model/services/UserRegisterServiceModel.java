package com.example.demo.model.services;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

public class UserRegisterServiceModel {

	private String username;
	
	private String email;
	
	private String password;

	private String confirmPassword;

	public UserRegisterServiceModel() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}