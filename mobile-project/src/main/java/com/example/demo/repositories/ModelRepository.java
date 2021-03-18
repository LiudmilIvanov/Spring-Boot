package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{

}
