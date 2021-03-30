package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


	@ModelAttribute("userModel")
	public UserLoginServiceModel userModel() {
		return new UserLoginServiceModel();
	}

	@GetMapping("/users/login")
	public String showLogin() {

		return "auth-login";
	}
	
	@PostMapping("/users/login")
	public String login(@Valid @ModelAttribute UserLoginServiceModel userModel, BindingResult bindingResult,
			RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("userModel", userModel);
			redirectAttribute.addFlashAttribute(" org.springframework.validation.BindingResult.userModel", bindingResult);
			
			return "redirect:/users/login";
		}
		
		if (userService.authenticate(userModel.getUsername(), userModel.getPassword())) {
			userService.loginUser(userModel.getUsername());
			return "redirect:/";
		} else {
			redirectAttribute.addFlashAttribute("userModel", userModel);
			redirectAttribute.addFlashAttribute("notFound", true);
			
			return "redirect:/users/login";
		}
	}
	
	@PostMapping("/users/logout")
	public String logout() {
		userService.logoutCurrentUser();
		
		return "redirect:/";
	}
	
}
