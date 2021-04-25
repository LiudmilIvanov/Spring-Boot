package com.example.demo.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/user")
public class ProfileController {

	private final UserService userService;
	private final OrderService orderService;
	
	@Autowired
	public ProfileController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}




	@GetMapping("/profile")
	public String getProfile(Model model, Principal principal) {
		
		model.addAttribute("user", userService.findByName(principal.getName()));
		model.addAttribute("orders", orderService.getPaidOrders());
		
		return "profile";
	}
	
	@GetMapping("/profile/details/{id}")
	public String viewDetails(@PathVariable Long id, Model model) {
		model.addAttribute("order", orderService.getOrderById(id));
		
		return "details";
	}
}
