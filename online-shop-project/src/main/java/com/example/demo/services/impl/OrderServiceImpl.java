package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
		return orderRepository.findByIfPaidFalse();
	}



	@Override
	@Transactional
	public void addOrder(Long id, String name) {
		List<OrderEntity> orders = orderRepository
				.findAllByUserAndIfPaidFalse(userService.findByName(name));
		System.out.println();
		if (!orders.isEmpty()) {
					List<ProductEntity> products = orders.get(0).getProducts();
				
				if (products.contains(productService.findById(id))) {
					return;
					
				} else {
					products.add(productService.findById(id));
					
					orders.get(0).setTotalSum(calculateTotalSum(orders.get(0).getProducts()));
					orders.get(0).setProducts(products);
					
				}
		
			
		} else {
			OrderEntity order = new OrderEntity();
			order.setDate(LocalDateTime.now());
			order.setUser(userService.findByName(name));
			order.setProducts(List.of(productService.findById(id)));
			order.setTotalSum(calculateTotalSum(order.getProducts()));
			
			
			orderRepository.save(order);
			System.out.println();
		}
		
		
	}
	


	@Override
	public void delete() {
		orderRepository.deleteAll();
		
	}



	@Override
	public BigDecimal getTotalSum() {
		List<OrderEntity> orders = orderRepository.findByIfPaidFalse();

		if (orders.isEmpty()) {
			BigDecimal totalPrice = new BigDecimal(0);

			return totalPrice;
		} else {
				
				BigDecimal totalPrice = orders.get(0).getProducts().stream()
						.map(ProductEntity::getPrice)
						.reduce(BigDecimal::add)
						.get();
				
				return totalPrice;
		}
		
 		
	}



	@Override
	@Transactional
	public void removeById(Long id, String name) {
		List<OrderEntity> orders = orderRepository
				.findAllByUserAndIfPaidFalse(userService.findByName(name));
		
		List<ProductEntity> products = orders.get(0).getProducts();
	    products.removeIf(p -> p.getId() == id);
		orders.get(0).setProducts(products);
			
	}



	@Override
	@Transactional
	public void buyProducts(String name) {
		List<OrderEntity> orders = orderRepository
				.findAllByUserAndIfPaidFalse(userService.findByName(name));
		
		if (!orders.isEmpty()) {
			System.out.println();
			orders.get(0).setIfPaid(true);
		}
		
	}



	@Override
	public List<OrderEntity> getPaidOrders() {
		return orderRepository.findByIfPaidTrue();
	}

	public BigDecimal calculateTotalSum(List<ProductEntity> products) {

		BigDecimal totalPrice = products.stream()
				.map(ProductEntity::getPrice)
				.reduce(BigDecimal::add)
				.get();
		
		return totalPrice;
		
	}



	@Override
	public OrderEntity getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

	



}
