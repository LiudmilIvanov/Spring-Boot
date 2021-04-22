package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.OrderEntity;
import com.example.demo.model.entities.UserEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

//	public boolean findByIfPaidFalse();
	
	public List<OrderEntity> findAllByUserAndIfPaidFalse(UserEntity user);
	
	public List<OrderEntity> findByIfPaidFalse();
}
