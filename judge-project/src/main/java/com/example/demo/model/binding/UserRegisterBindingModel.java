package com.example.demo.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {
	
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String git;

	public UserRegisterBindingModel() {

	}

	@Length(min = 2, message = "Username lenght must be at least 3 characters!")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min = 3, message = "Password lenght must be at least 3 characters!")
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

	@Email(message = "Enter valid email address!")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "https:\\/\\/github\\.com\\/.+", message = "Enter a valid git address!")
	public String getGit() {
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}
	
	
	
}
