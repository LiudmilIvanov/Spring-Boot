package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.entity.enums.ModelCategory;

import io.micrometer.core.annotation.Counted;

@Entity
@Table(name = "model")
public class Model extends BaseEntity{

	@Column
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ModelCategory category;
	
	@Column(length = 512)
	private String imageUrl;
	
	@Column
	private int startYear;
	
	@Column(nullable = true)
	private Integer endYear;

	@ManyToOne
	private Brand brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ModelCategory getCategory() {
		return category;
	}

	public void setCategory(ModelCategory category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Model() {

	}
	
	
	
}
