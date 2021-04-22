package com.example.demo.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

	private final ProductService productService;
	private final OrderService orderService;
	
	@Autowired
	public ProductsController(ProductService productService, OrderService orderService) {
		this.productService = productService;
		this.orderService = orderService;
	}



	@GetMapping("/tea")
	public String tea(@RequestParam(name = "keyword", required = false) String keyword,
			Model model) {
		if (keyword != null) {
			model.addAttribute("tea", productService.findAllByName(keyword));
		} else {
			model.addAttribute("tea", productService.findAllProductsByCategoryName(CategoryTypeEnum.TEA));
			
		}
		
		return "product-tea";
	}
	
	
	
	@GetMapping("/coffee")
	public String coffee(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		if (keyword != null) {
			model.addAttribute("coffee", productService.findAllByName(keyword));
			
		} else {
			model.addAttribute("coffee", productService.findAllProductsByCategoryName(CategoryTypeEnum.COFFEE));
			
		}
		
		return "product-coffee";
	}
	
	@GetMapping("/nuts")
	public String nuts(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		if (keyword != null) {
			model.addAttribute("nuts", productService.findAllByName(keyword));
		} else {
			model.addAttribute("nuts", productService.findAllProductsByCategoryName(CategoryTypeEnum.NUTS));
		}
		
		return "product-nuts";
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
	
	@GetMapping("/buy/{id}")
	public String buyProduct(@PathVariable Long id, Principal principal, Model model) {
	//	String path = request.getContextPath();
		if (!model.containsAttribute("productAddBindingModel")) {
			model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
			System.out.println();
		}
	
		//TODO add relative path.
		orderService.addOrder(id, principal.getName());
		
		
		return "redirect:/products/info/{id}";
	}
	
	
	@PostMapping("/info/{id}")
	public String buyProductConfirm(@ModelAttribute ProductAddBindingModel productAddBindingModel, @PathVariable Long id, Principal principal,
		 BindingResult bindingResult) {
		System.out.println();
		orderService.addOrder(id, principal.getName());
		return "redirect:/products/info/{id}";
	}
	
/*	@PostMapping("/buy/{id}") 
	public string buyProductConfirm() {
		
		return 
	}*/
	
	
	@GetMapping("/info/{id}")
	public String viewProductDetails(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "product-info";
	}
	
	
	
	/*@PostMapping("/tea")
	public String viewProducts() {
		
		return "products-tea";
	}*/
}
