package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.CategoryServiceModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
	}



	@Override
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}



	@Override
	@Transactional
	public void addProduct(ProductAddBindingModel productAddBindingModel) {
		ProductEntity product =  modelMapper.map(productAddBindingModel, ProductEntity.class);
		product.setCategory(categoryRepository.findByName(productAddBindingModel.getCategory()));
		//product.setPrice(new BigDecimal(1));
		product.setQuantity(1);
		
		
		productRepository.save(product);
	}



	@Override
	public List<ProductEntity> findAllProductsByCategoryName(CategoryTypeEnum categoryName) {
		return productRepository.findAllByCategory_Name(categoryName);
	}



	@Override
	public ProductEntity findById(Long id) {
		return productRepository.findById(id).orElse(null);
		
	}



	@Override
	public List<ProductEntity> findAllByName(String name) {
		return productRepository.findAllByName(name);
	}



	






	
}
