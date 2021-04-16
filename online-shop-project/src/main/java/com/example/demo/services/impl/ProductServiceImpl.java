package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.repository.ProductRepository;
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
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}



	@Override
	public void addProduct(ProductAddBindingModel productAddBindingModel) {
		ProductEntity product =  modelMapper.map(productAddBindingModel, ProductEntity.class);
		product.setPrice(new BigDecimal(1));
		product.setQuantity(1);
		System.out.println();
		
		productRepository.save(product);
	}

}
