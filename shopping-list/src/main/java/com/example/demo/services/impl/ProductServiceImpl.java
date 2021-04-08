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
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	@Transactional
	public void save(ProductAddServiceModel productServiceModel) {
		Product product = modelMapper.map(productServiceModel, Product.class);
		// TODO crate method that fetch category from the DB and set it to the product
		
		
		
		productRepository.saveAndFlush(product);
	}

}
