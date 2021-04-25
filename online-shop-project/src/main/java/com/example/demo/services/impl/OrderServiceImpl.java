package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.OrderEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.model.services.OrderServiceModel;
import com.example.demo.model.services.ProductServiceModel;
import com.example.demo.repository.OrderRepository;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final ProductService productService;
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, 
			UserService userService, ModelMapper modelMapper) {
		this.orderRepository = orderRepository;
		this.productService = productService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}



	@Override
	public List<OrderServiceModel> getAllOrders() {
		List<OrderServiceModel> orderServiceModels = orderRepository.findByIfPaidFalse()
				.stream()
				.map(o -> {
			OrderServiceModel orderServiceModel = modelMapper.map(o, OrderServiceModel.class);
			
			orderServiceModel.getProducts().stream().map(p -> {
				ProductServiceModel productServiceModel = modelMapper.map(p,ProductServiceModel.class);
				
				return productServiceModel;
			}).collect(Collectors.toList());
			
			return orderServiceModel;
		}).collect(Collectors.toList());
				
		return orderServiceModels;
	}



	@Override
	@Transactional
	public void addOrder(Long id, String name) {
		List<OrderEntity> orders = orderRepository
				.findAllByUserAndIfPaidFalse(modelMapper.map(userService.findByName(name), UserEntity.class));

		if (!orders.isEmpty()) {
					List<ProductEntity> products = orders.get(0).getProducts();
				
				if (products.contains(modelMapper.map(productService.findById(id), ProductEntity.class))) {
					return;
					
				} else {
					products.add(modelMapper.map(productService.findById(id), ProductEntity.class));
					
					orders.get(0).setTotalSum(calculateTotalSum(orders.get(0).getProducts()));
					orders.get(0).setProducts(products);
					
				}
		
			
		} else {
			OrderEntity order = new OrderEntity();
			order.setDate(LocalDateTime.now());
			order.setUser(modelMapper.map(userService.findByName(name), UserEntity.class));
			order.setProducts(List.of(modelMapper.map(productService.findById(id), ProductEntity.class)));
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
				.findAllByUserAndIfPaidFalse(modelMapper
						.map(userService.findByName(name), UserEntity.class));
		
		List<ProductEntity> products = orders.get(0).getProducts();
	    products.removeIf(p -> p.getId() == id);
		orders.get(0).setProducts(products);
			
	}



	@Override
	@Transactional
	public void buyProducts(String name) {
		List<OrderEntity> orders = orderRepository
				.findAllByUserAndIfPaidFalse(modelMapper
						.map(userService.findByName(name), UserEntity.class));
		
		if (!orders.isEmpty()) {
			orders.get(0).setIfPaid(true);
		}
		
	}



	@Override
	public List<OrderServiceModel> getPaidOrders() {
		List<OrderServiceModel> orderServiceModels = orderRepository.findByIfPaidTrue()
				.stream()
				.map(o -> {
			OrderServiceModel orderServiceModel = modelMapper.map(o, OrderServiceModel.class);
			
			orderServiceModel.getProducts().stream().map(p -> {
				ProductServiceModel productServiceModel = modelMapper.map(p,ProductServiceModel.class);
				
				return productServiceModel;
			}).collect(Collectors.toList());
			
			return orderServiceModel;
		}).collect(Collectors.toList());
		
		
		return orderServiceModels;
	}

	public BigDecimal calculateTotalSum(List<ProductEntity> products) {

		BigDecimal totalPrice = products.stream()
				.map(ProductEntity::getPrice)
				.reduce(BigDecimal::add)
				.get();
		
		return totalPrice;
		
	}



	@Override
	public OrderServiceModel getOrderById(Long id) {
		Optional<OrderEntity> order = orderRepository.findById(id);
		
		if (!order.isPresent()) {
			throw new EntityNotFoundException("Order not found!");
		} else {
			
			OrderServiceModel orderServiceModel = modelMapper
					.map(orderRepository.findById(id).get(), OrderServiceModel.class) ;

			return orderServiceModel;
		}
		
	}

	



}
