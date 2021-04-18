package com.example.demo.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.OrderEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.repository.OrderRepository;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final ProductService productService;
	private final UserService userService;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, UserService userService) {
		this.orderRepository = orderRepository;
		this.productService = productService;
		this.userService = userService;
	}



	@Override
	public List<OrderEntity> getAllOrders() {
		return orderRepository.findAll();
	}



	@Override
	@Transactional
	public void addOrder(Long id, String name) {
		if (orderRepository.count() > 0) {
			List<OrderEntity> orders = orderRepository
					.findAllByUserAndIfPaidFalse(userService.findByName(name));
			List<ProductEntity> products = orders.get(0).getProducts();
			products.add(productService.findById(id));
			
			orders.get(0).setProducts(products);
		} else {
			OrderEntity order = new OrderEntity();
			order.setDate(LocalDateTime.now());
			order.setUser(userService.findByName(name));
			order.setProducts(List.of(productService.findById(id)));
		
			orderRepository.save(order);
			
		}
		
		
	}



	@Override
	public void delete() {
		orderRepository.deleteAll();
		
	}

}
