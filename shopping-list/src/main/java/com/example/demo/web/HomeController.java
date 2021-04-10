package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final UserService userService;
	private final ProductService productService; 
	
	
	@Autowired
	public HomeController(UserService userService, ProductService productService) {
		this.userService = userService;
		this.productService = productService;
	}


	@GetMapping("/")
	public String homePage(Model model) {
		if (userService.isLogged()) {
			model.addAttribute("totalSum", productService.getTotalSum());
			model.addAttribute("drinks", productService.findAllProductsByCategoryName(CategoryTypeEnum.DRINK));
			
			
			return "home";
		} else {
			return "index";
		}
	}
}
