package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.entity.enums.RoleName;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final UserService userService;
	
	
	@Autowired
	public RoleController(UserService userService) {
		this.userService = userService;
	}



	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("names", userService.findAllUsernames());
		
		return "role-add";
	}
	
	@PostMapping("/add")
	public String addConfirm(@RequestParam String username, @RequestParam String role) {
		
		userService.changeRole(username, RoleName.valueOf(role.toUpperCase()));
		
		return "redirect:/";
	}
}
