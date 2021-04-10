package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.ProductAddServiceModel;
import com.example.demo.model.view.ProductViewModel;

public interface ProductService {

	public void save(ProductAddServiceModel productServiceModel);
	
	BigDecimal getTotalSum();
	
	List<ProductViewModel> findAllProductsByCategoryName(CategoryTypeEnum categoryName);

	public void buyById(Long id);

	public void buyAll();
}
