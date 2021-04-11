package com.example.demo.model.view;

import java.util.Set;

public class UserProfileViewModel {

	private String username;
	private Set<String> homeworkSet;
	private String email;
	private String git;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<String> getHomeworkSet() {
		return homeworkSet;
	}
	public void setHomeworkSet(Set<String> homeworkSet) {
		this.homeworkSet = homeworkSet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}

	
}
