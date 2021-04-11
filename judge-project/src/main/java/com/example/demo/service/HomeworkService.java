package com.example.demo.service;

import javax.validation.constraints.Pattern;

import com.example.demo.model.entity.Homework;
import com.example.demo.model.service.HomeworkServiceModel;

public interface HomeworkService {

	public void addHomework(String exercise, String gitAddress);

	public HomeworkServiceModel findHomeworkByScore();

	public Homework findById(Long homeworkId);

}
