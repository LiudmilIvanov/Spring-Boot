package com.example.demo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

	private static final String ANNONYMOUS = "annonymous";
	private String name = "ANNONYMOUS";
	private boolean isAnnonymous = true;
	
	
	public CurrentUser() {

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
		}
		this.isAnnonymous = isAnnonymous;
		return this;
	}

	public static String getAnnonymous() {
		return ANNONYMOUS;
	}
	
	
	
}
