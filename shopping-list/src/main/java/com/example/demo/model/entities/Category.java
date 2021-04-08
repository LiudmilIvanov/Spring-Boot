package com.example.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.demo.model.enums.CategoryTypeEnum;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

	@Column
	@Enumerated(EnumType.STRING)
	private CategoryTypeEnum name;
	
	@Column
	private String description;

	public CategoryTypeEnum getName() {
		return name;
	}

	public void setName(CategoryTypeEnum name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category() {
		super();
	}

	public Category(CategoryTypeEnum name) {
		super();
		this.name = name;
	}
	
	
	
}
