package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.RoleTypeEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	RoleEntity findByName(RoleTypeEnum name);
	
}
