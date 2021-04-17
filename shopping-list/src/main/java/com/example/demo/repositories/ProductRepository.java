package com.example.demo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Product;
import com.example.demo.model.enums.CategoryTypeEnum;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT SUM(p.price) FROM Product p ")
	BigDecimal findTotalProductsSum();

	List<Product> findAllByCategory_Name(CategoryTypeEnum categoryName);

	
	
}
