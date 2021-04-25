package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.ProductServiceModel;

public interface ProductService {

	List<ProductServiceModel> getAllProducts();

	void addProduct(ProductAddBindingModel productAddBindingModel);

	List<ProductServiceModel> findAllProductsByCategoryName(CategoryTypeEnum categoryName);

	ProductServiceModel findById(Long id);

	List<ProductServiceModel> findAllByName(String name);
	
	public ProductServiceModel getRandomProduct();
	
	public BigDecimal getTotalSum();
	

}
