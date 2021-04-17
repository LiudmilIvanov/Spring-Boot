package com.example.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.demo.model.enums.CategoryTypeEnum;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

	@Column
	@Enumerated(EnumType.STRING)
	private CategoryTypeEnum name;

	public CategoryTypeEnum getName() {
		return name;
	}

	public void setName(CategoryTypeEnum name) {
		this.name = name;
	}

	public CategoryEntity(CategoryTypeEnum name) {
		this.name = name;
	}

	public CategoryEntity() {

	}
	
	
	
}
