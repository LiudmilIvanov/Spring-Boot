package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	List<ProductEntity> findAllByCategory_Name(CategoryTypeEnum categoryName);
}
