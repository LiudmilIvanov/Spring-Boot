package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.UserRegisterBindingModel;

@Controller
@RequestMapping("/users")
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return "redirect:register";
		}
		
		return "redirect:login";
	}
	
}

