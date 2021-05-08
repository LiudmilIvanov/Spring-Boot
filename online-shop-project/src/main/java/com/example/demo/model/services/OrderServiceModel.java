package com.example.demo.model.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.entities.UserEntity;

public class OrderServiceModel {

	private Long id;
	
	private LocalDateTime date;

	private boolean ifPaid = false;
	
	private BigDecimal totalSum;
	
	private List<ProductServiceModel> products;
	
	private UserEntity user;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isIfPaid() {
		return ifPaid;
	}

	public void setIfPaid(boolean ifPaid) {
		this.ifPaid = ifPaid;
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public List<ProductServiceModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductServiceModel> products) {
		this.products = products;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
}
