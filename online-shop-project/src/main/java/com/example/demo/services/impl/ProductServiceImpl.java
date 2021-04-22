package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
		System.out.println();
		
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



	@Override
	public ProductEntity getRandomProduct() {
		Random random = new Random();

		long numberOfProducts = productRepository.count() - 1;
		int productCounter = random.nextInt((int) numberOfProducts) + 1;
		
		Optional<ProductEntity> product = productRepository.findById((long) productCounter);
		
		if (!product.isPresent()) {
			throw new EntityNotFoundException("Product is missing!");
		}
		
		return product.get();
	}


	@Override
	public BigDecimal getTotalSum() {
		if (productRepository.count() == 0) {
			return new BigDecimal(0);
		}
		
		return productRepository.findTotalProductsSum();
	}
	






	
}
