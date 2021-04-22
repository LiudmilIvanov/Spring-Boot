package com.example.demo.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.UserService;

@Controller
@RequestMapping("/user")
public class ProfileController {

	private final UserService userService;
	
	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;
	}




	@GetMapping("/profile")
	public String getProfile(Model model, Principal principal) {
		
		model.addAttribute("user", userService.findByName(principal.getName()));
		
		return "profile";
	}
}
