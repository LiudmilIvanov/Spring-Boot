package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.service.UserLoginServiceModel;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.UserService;

@Controller
public class LoginController {
	
	private final CurrentUser currentUser;
	private final UserService userService;
	
	@Autowired
	public LoginController(CurrentUser currentUser, UserService userService) {
		this.currentUser = currentUser;
		this.userService = userService;
	}



	@GetMapping("/users/login")
	public String showLogin() {
	
		return "auth-login";
	}
	
	@PostMapping("/users/login")
	public String login(UserLoginServiceModel model) {
		if (userService.authenticate(model.getUsername(), model.getPassword())) {
			userService.loginUser(model.getUsername());
			return "redirect:/";
		} else {
			return "redirect:/users/login";
		}
	}
	
}
