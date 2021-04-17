package com.example.demo.services;

import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.CategoryServiceModel;

public interface CategoryService {

	public CategoryServiceModel findByName(CategoryTypeEnum categoryName);
	
	public boolean ifCategoryExist();

	public void saveCategories(CategoryEntity category);
}
