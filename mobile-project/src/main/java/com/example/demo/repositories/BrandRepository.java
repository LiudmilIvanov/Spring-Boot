package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

	Brand findByName(String name);
}
