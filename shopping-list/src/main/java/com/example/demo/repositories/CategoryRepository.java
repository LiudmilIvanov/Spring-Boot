package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Category;
import com.example.demo.model.enums.CategoryTypeEnum;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category findByName(CategoryTypeEnum name);
}
