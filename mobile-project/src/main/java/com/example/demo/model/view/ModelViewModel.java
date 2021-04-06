package com.example.demo.model.view;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.example.demo.model.entity.Brand;
import com.example.demo.model.entity.enums.ModelCategory;

public class ModelViewModel {

	private long id; 
	private String name;
	private ModelCategory category;
	private String imageUrl;
	private int startYear;
	private Integer endYear;
	
	public ModelViewModel() {
	
	}
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
	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	
}
