package com.example.demo.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.UserLoginBindingModel;
import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.services.UserRegisterServiceModel;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/")
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
			redirectAttribute.addAttribute("userLoginBindingModel" , userLoginBindingModel);
			redirectAttribute.addAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
		
			return "redirect:/login";
		}

		//TODO
		
		return "redirect:/login";
	}
	
	@RequestMapping("/login-err")
	public String loginErr(Model model) {
		
		model.addAttribute("loginErr", true);
		return "redirect:/login";
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		if (!model.containsAttribute("userRegisterBindingModel")) {
			model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
		}
		return "register";
	}
	
	@PostMapping("/register")
	public String registerCondirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
			redirectAttribute.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", 
					bindingResult);
			System.out.println();
		return "redirect:/register";
		}
		
		if (userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
			UserRegisterServiceModel userRegisterServiceModel =
					modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);
			
			userService.save(userRegisterServiceModel);

			return "redirect:/login";
		} else {
			redirectAttribute.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
			System.out.println();
			return "redirect:/register";
		
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			
			return "redirect:/";
		}
		
		return "redirect:/";
	}
	
}
