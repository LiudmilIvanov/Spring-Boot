package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.entities.OrderEntity;

public interface OrderService {

	public List<OrderEntity> getAllOrders();

	public void addOrder(Long id, String name);

	public void delete();
	
	public BigDecimal getTotalSum();

	public void removeById(Long id, String name);
	
	public void buyProducts(String name);
	
	public List<OrderEntity> getPaidOrders();
	
	public OrderEntity getOrderById(Long id);

}
