package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.security.CurrentUser;

@Controller
public class HomeController {

	private final CurrentUser currentUser;
	
	@Autowired
	public HomeController(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}



	@GetMapping("/")
	public String index() {
		return currentUser.isAnonymous() ?  "index" : "home";
	}
	
}
