package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.model.entity.enums.UserRoleEnum;

@Component
@SessionScope
public class CurrentUser {

	private static final String ANNONYMOUS = "annonymous";
	private String name = "ANNONYMOUS";
	private boolean isAnnonymous = true;
	private List<UserRoleEnum> userRoles = new ArrayList<>();
	
	
	public CurrentUser() {

	}
	
	public boolean isAdmin() {
		return userRoles.contains(UserRoleEnum.ADMIN);
	}
	
	
	public List<UserRoleEnum> getUserRoles() {
		return userRoles;
	}



	public CurrentUser setUserRoles(List<UserRoleEnum> newRoles) {
		userRoles.clear();
		userRoles.addAll(newRoles);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAnnonymous() {
		return isAnnonymous;
	}
	
	public boolean isLoggedIn() {
		return !isAnnonymous;
	}

	public CurrentUser setAnnonymous(boolean isAnnonymous) {
		if (isAnnonymous) {
			this.name = ANNONYMOUS;
			this.userRoles.clear();
		}
		this.isAnnonymous = isAnnonymous;
		return this;
	}

	public static String getAnnonymous() {
		return ANNONYMOUS;
	}
	
	
	
}
