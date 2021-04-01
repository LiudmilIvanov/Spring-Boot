package com.example.demo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.model.entity.enums.RoleName;

@Component
@SessionScope
public class CurrentUser {

	private Long id;
	private String username;
	private RoleName role;
	
	public Long getId() {
		return id;
	}
	public CurrentUser setId(Long id) {
		this.id = id;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public CurrentUser setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public boolean isAnonymous() {
		return this.username == null;
	}
	
	public boolean isAdmin() {
		return	this.role.equals(RoleName.ADMIN);
	}
	public RoleName getRole() {
		return role;
	}
	public CurrentUser setRole(RoleName role) {
		this.role = role;
		return this;
	}

	
}
