package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.enums.CategoryTypeEnum;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	
	public CategoryEntity findByName(CategoryTypeEnum name);
	
}
