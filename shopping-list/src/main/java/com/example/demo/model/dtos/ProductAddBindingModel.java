package com.example.demo.model.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.entities.Category;
import com.example.demo.model.enums.CategoryTypeEnum;

public class ProductAddBindingModel {

	@Length(min = 3, max = 20)
	private String name;
	
	@Length(min = 5)
	private String description;

	@DecimalMin(value = "0.0")
	private BigDecimal price;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@FutureOrPresent(message = "Date cannot be in the past!")
	private LocalDateTime neededBefore;
	
	private CategoryTypeEnum category;
	

	public CategoryTypeEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryTypeEnum category) {
		this.category = category;
	}

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

	
	
}
