package com.example.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dtos.ProductAddBindingModel;
import com.example.demo.model.services.ProductAddServiceModel;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ProductController(ProductService productService, ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/add")
	public String addProduct(Model model) {
		if (!model.containsAttribute("productAddBindingModel")) {
			model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
			
		}
		return "product-add";
	}
	
	@PostMapping("/add")
	public String addConfirm(@ModelAttribute ProductAddBindingModel productAddBindingModel ) {
		
		productService.save(modelMapper.map(productAddBindingModel, ProductAddServiceModel.class));
		
		System.out.println();
		return "redirect:/home";
	}
	
}
