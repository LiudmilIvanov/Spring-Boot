package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{

}
