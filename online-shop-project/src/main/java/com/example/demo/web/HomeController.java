package com.example.demo.web;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.view.ProductViewModel;
import com.example.demo.services.ProductService;

@Controller
public class HomeController {

	private final ProductService productService;

	@Autowired
	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	@ExceptionHandler({ EntityNotFoundException.class })
	public String indexPage(Model model) {
		model.addAttribute("productOne", productService.getRandomProduct());
		model.addAttribute("productTwo", productService.getRandomProduct());
		model.addAttribute("productThree", productService.getRandomProduct());
		model.addAttribute("productFour", productService.getRandomProduct());
		model.addAttribute("productFive", productService.getRandomProduct());

		return "home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

}
