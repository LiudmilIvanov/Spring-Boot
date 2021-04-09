package com.example.demo.services.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.Category;
import com.example.demo.model.entities.Product;
import com.example.demo.model.services.ProductAddServiceModel;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper
			,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
	}



	@Override
	@Transactional
	public void save(ProductAddServiceModel productServiceModel) {
		Product product = modelMapper.map(productServiceModel, Product.class);
		product.setCategory(categoryRepository.findByName(productServiceModel.getCategory()));

		productRepository.saveAndFlush(product);
	}

}
