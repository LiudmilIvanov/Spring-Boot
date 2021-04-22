package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;

public interface ProductService {

	List<ProductEntity> getAllProducts();

	void addProduct(ProductAddBindingModel productAddBindingModel);

	List<ProductEntity> findAllProductsByCategoryName(CategoryTypeEnum categoryName);

	ProductEntity findById(Long id);

	List<ProductEntity> findAllByName(String name);
	
	public ProductEntity getRandomProduct();
	

}
