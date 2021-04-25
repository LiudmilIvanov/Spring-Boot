package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.CategoryServiceModel;
import com.example.demo.model.services.ProductServiceModel;
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
	public List<ProductServiceModel> getAllProducts() {
		return productRepository.findAll().stream().map(p -> {
			ProductServiceModel productServiceModel = modelMapper.map(p, ProductServiceModel.class);
			
			return productServiceModel;
		}).collect(Collectors.toList());
	}



	@Override
	@Transactional
	public void addProduct(ProductAddBindingModel productAddBindingModel) {
		ProductEntity product =  modelMapper.map(productAddBindingModel, ProductEntity.class);
		product.setCategory(categoryRepository.findByName(productAddBindingModel.getCategory()));
		product.setQuantity(1);
		
		productRepository.save(product);
	}



	@Override
	public List<ProductServiceModel> findAllProductsByCategoryName(CategoryTypeEnum categoryName) {
		return productRepository.findAllByCategory_Name(categoryName)
				.stream()
				.map(p -> {
			ProductServiceModel productServiceModel = modelMapper.map(p, ProductServiceModel.class);
			
			return productServiceModel;
		}).collect(Collectors.toList());
	}



	@Override
	public ProductServiceModel findById(Long id) {
		Optional<ProductEntity> product = productRepository.findById(id); 
		
		if (!product.isPresent()) {
			throw new EntityNotFoundException("Product cannot be found!");
		} else {
			return modelMapper.map(product.get(), ProductServiceModel.class);
		}
		
	}



	@Override
	public List<ProductServiceModel> findAllByName(String name) {
		return productRepository.findAllByName(name).stream().map(p -> {
			ProductServiceModel productServiceModel = modelMapper.map(p, ProductServiceModel.class);
			
			return productServiceModel;
		}).collect(Collectors.toList());
	}



	@Override
	public ProductServiceModel getRandomProduct() {
		Random random = new Random();

		long numberOfProducts = productRepository.count() - 1;
		int productCounter = random.nextInt((int) numberOfProducts) + 1;
		
		Optional<ProductEntity> product = productRepository.findById((long) productCounter);
		
		if (!product.isPresent()) {
			throw new EntityNotFoundException("Product is missing!");
		}
		return modelMapper
				.map(product.get(), ProductServiceModel.class);
	}


	@Override
	public BigDecimal getTotalSum() {
		if (productRepository.count() == 0) {
			return new BigDecimal(0);
		}
		
		return productRepository.findTotalProductsSum();
	}
	






	
}
