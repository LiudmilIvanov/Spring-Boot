package com.example.demo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.model.enums.RoleTypeEnum;

@Component
@SessionScope
public class CurrentUser {

	private long id;
	private String username;
	RoleTypeEnum role;
	
	public boolean isAnonymous() {
		return username == null;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RoleTypeEnum getRole() {
		return role;
	}
	public void setRole(RoleTypeEnum role) {
		this.role = role;
	}
	
	
	
}
