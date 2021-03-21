package com.example.demo.web;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final ModelMapper modelMapper;
	
	
	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}


	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		if (!model.containsAttribute("userRegisterBindingModel")) {
			model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
		}
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
				.equals(userRegisterBindingModel.getConfirmPassword())) {
			redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", 
					bindingResult);
			
			return "redirect:register";
		}
		UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
		this.userService.registerUser(userServiceModel);
		
		return "redirect:login";
	}
	
}
