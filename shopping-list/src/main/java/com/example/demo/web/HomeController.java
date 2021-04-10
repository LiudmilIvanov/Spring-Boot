package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final UserService userService;
	
	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/")
	public String homePage() {
		if (userService.isLogged()) {
			return "home";
		} else {
			return "index";
		}
	}
}
