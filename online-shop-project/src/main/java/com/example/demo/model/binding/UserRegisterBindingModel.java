package com.example.demo.model.binding;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {

	@Length(min = 3, max = 20)
	private String username;
	
	@Email
	private String email;
	
	@Length(min = 3, max = 20)
	private String password;

	@Length(min = 3, max = 20)
	private String confirmPassword;

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
