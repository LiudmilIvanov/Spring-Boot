package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.entities.OrderEntity;
import com.example.demo.model.services.OrderServiceModel;

public interface OrderService {

	public List<OrderServiceModel> getAllOrders();

	public void addOrder(Long id, String name);

	public void delete();
	
	public BigDecimal getTotalSum();

	public void removeById(Long id, String name);
	
	public OrderServiceModel buyProducts(String name);
	
	public List<OrderServiceModel> getPaidOrders();
	
	public OrderServiceModel getOrderById(Long id);

}
