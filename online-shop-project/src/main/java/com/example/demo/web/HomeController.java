package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}


	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/products")
	public String products() {
		return "products";
	}

	@GetMapping("/orders")
	public String orders() {
		return "orders";
	}
	
	@GetMapping("/totalsum")
	public String totalsum() {
		return "totalsum";
	}
	
	@GetMapping("/update")
	public String update() {
		return "updateInformation";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
}
