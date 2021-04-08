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

import com.example.demo.model.dtos.UserLoginBindingModel;
import com.example.demo.model.dtos.UserRegisterBindingModel;
import com.example.demo.model.services.UserRegisterServiceModel;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	
	@GetMapping("/login")
	public String login(Model model) {
		if (!model.containsAttribute("userLoginBindingModel")) {
			model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
			
		}
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
			redirectAttribute.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
			
			return "redirect:/user/login";
		}
		
		
		if (userService.authenticate(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())) {
			userService.login(userLoginBindingModel);

		return "redirect:/home";
		}
		
		
		return "redirect:/home";
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
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
			redirectAttribute.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
			
			return "redirect:/user/register";
		}
		
		if (userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
			userService.save(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));
			
			return "redirect:/user/login";
		} else {
			redirectAttribute.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
			
			return "redirect:/user/register";
		}
		
	}
}
