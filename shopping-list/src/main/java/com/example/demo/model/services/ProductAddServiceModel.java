package com.example.demo.model.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.example.demo.model.enums.CategoryTypeEnum;

public class ProductAddServiceModel {


	private String name;
	private String description;
	private BigDecimal price;
	private LocalDateTime neededBefore;
	private CategoryTypeEnum category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public LocalDateTime getNeededBefore() {
		return neededBefore;
	}
	public void setNeededBefore(LocalDateTime neededBefore) {
		this.neededBefore = neededBefore;
	}
	public CategoryTypeEnum getCategory() {
		return category;
	}
	public void setCategory(CategoryTypeEnum category) {
		this.category = category;
	}

	
}
