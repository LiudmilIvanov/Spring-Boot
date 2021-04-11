package com.example.demo.service;

import com.example.demo.model.service.CommentServiceModel;

public interface CommentService {

	void add(CommentServiceModel commentServiceModel, Long homeworkId);

}
