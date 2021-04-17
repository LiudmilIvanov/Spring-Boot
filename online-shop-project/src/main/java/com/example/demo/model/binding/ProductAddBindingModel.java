package com.example.demo.model.binding;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.example.demo.model.enums.CategoryTypeEnum;

public class ProductAddBindingModel {

	private String name;
	
	private String imageUrl;
	
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
