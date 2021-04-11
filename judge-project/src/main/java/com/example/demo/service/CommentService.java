package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.service.CommentServiceModel;

public interface CommentService {

	void add(CommentServiceModel commentServiceModel, Long homeworkId);

	Double findAvgScore();

	Map<Integer, Integer> findScoreMap();

}
