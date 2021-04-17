package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.CategoryServiceModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public CategoryServiceModel findByName(CategoryTypeEnum categoryName) {
		CategoryEntity category = categoryRepository.findByName(categoryName);
		
		System.out.println();
		return modelMapper
				.map(category, CategoryServiceModel.class);
	}



	@Override
	public boolean ifCategoryExist() {
		return categoryRepository.count() > 0;
	}



	@Override
	public void saveCategories(CategoryEntity category) {
		categoryRepository.save(category);
	}

}
