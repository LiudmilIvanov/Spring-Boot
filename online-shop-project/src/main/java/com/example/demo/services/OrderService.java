package com.example.demo.services;

import java.util.List;

import com.example.demo.model.entities.OrderEntity;

public interface OrderService {

	public List<OrderEntity> getAllOrders();

	public void addOrder(Long id, String name);

	public void delete();
	
	
}
