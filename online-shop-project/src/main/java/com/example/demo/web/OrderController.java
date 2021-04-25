package com.example.demo.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/")
public class OrderController {

	private final OrderService orderService;
	private final ProductService productService;
	
	@Autowired
	public OrderController(OrderService orderService, ProductService productService) {
		this.orderService = orderService;
		this.productService = productService;
	}




	@GetMapping("/cart")
	public String viewOrders(Model model) {
		model.addAttribute("orders", orderService.getAllOrders());
	//	model.addAttribute("totalSum", orderService.getTotalSum());
		return "cart";
	}
	
	@GetMapping("/cart/delete/{id}")
	public String removeProduct(@PathVariable Long id, Principal principal) {
		orderService.removeById(id, principal.getName());
		
		return "redirect:/cart";
		
	}
	
	@GetMapping("/cart/buy")
	public String proceed(Principal principal) {
		orderService.buyProducts(principal.getName());
		
		
		return "redirect:/";
	}
}
