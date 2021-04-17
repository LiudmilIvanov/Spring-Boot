package com.example.demo.services;

import java.util.List;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.ProductEntity;

public interface ProductService {

	List<ProductEntity> getAllProducts();

	void addProduct(ProductAddBindingModel productAddBindingModel);

}