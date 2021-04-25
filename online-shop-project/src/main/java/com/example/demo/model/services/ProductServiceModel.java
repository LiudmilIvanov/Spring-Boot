package com.example.demo.model.services;

import java.math.BigDecimal;

import com.example.demo.model.entities.CategoryEntity;

public class ProductServiceModel {

	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private long quantity;
	
	private String imageUrl;
	
	private CategoryEntity category;
	
	private String description;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public ProductServiceModel(Long id, String name, BigDecimal price, long quantity, String imageUrl,
			CategoryEntity category, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
		this.category = category;
		this.description = description;
	}

	public ProductServiceModel() {

	}
	
	
	
	
}
