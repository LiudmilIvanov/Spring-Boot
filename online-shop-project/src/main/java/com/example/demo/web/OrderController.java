package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.OrderService;

@Controller
@RequestMapping("/")
public class OrderController {

	private final OrderService orderService;
	
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}



	@GetMapping("/cart")
	public String viewOrders(Model model) {
		model.addAttribute("orders", orderService.getAllOrders());
		return "cart";
	}
	
}
