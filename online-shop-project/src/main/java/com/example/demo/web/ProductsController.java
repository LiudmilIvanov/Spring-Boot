package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.binding.ProductBindingModel;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("")
public class ProductsController {

	private final ProductService productService;
	
	
	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}



	@GetMapping("/tea")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products-tea";
	}
	
	/*@PostMapping("/tea")
	public String viewProducts() {
		
		return "products-tea";
	}*/
}
