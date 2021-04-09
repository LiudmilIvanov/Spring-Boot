package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.entities.Category;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;


@Component
public class DBInit implements CommandLineRunner{

	private final CategoryService categoryService;
	private final CategoryRepository categoryRepository;
	
	
	@Autowired
	public DBInit(CategoryService categoryService, CategoryRepository categoryRepository) {
		this.categoryService = categoryService;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		initCategories();
		
	}

	public void initCategories() {
		if (!categoryService.ifExist()) {
			Arrays.stream(CategoryTypeEnum.values())
				.forEach(categoryName -> {
					Category category = new Category(categoryName);
					
					categoryRepository.save(category);
					
				});
		}
	}

}
