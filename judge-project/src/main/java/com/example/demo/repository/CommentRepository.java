package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
