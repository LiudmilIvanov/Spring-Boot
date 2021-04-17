package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.binding.ProductBindingModel;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

	private final ProductService productService;
	
	
	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}



	@GetMapping("/tea")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "product-tea";
	}
	
	@GetMapping("/add")
	public String addProduct(Model model) {
		if (!model.containsAttribute("productAddBindingModel")) {
			model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
			
			return "products-add";
		}
		
		
		return "products-add";
	}
	
	@PostMapping("/add")
	public String addProductConfirm(@ModelAttribute ProductAddBindingModel productAddBindingModel) {
		
		System.out.println();
		productService.addProduct(productAddBindingModel);
		
		return "redirect:/products/add";
	}
	
	/*@PostMapping("/tea")
	public String viewProducts() {
		
		return "products-tea";
	}*/
}
