package com.example.demo.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Homework;
import com.example.demo.model.service.HomeworkServiceModel;
import com.example.demo.repository.HomeworkRepository;
import com.example.demo.security.CurrentUser;

@Service
public class HomeworkServiceImpl implements HomeworkService{

	private final HomeworkRepository homeworkRepository;
	private final ExerciseService exerciseService;
	private final CurrentUser currentUser;
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseServie,
			CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
		this.homeworkRepository = homeworkRepository;
		this.exerciseService = exerciseServie;
		this.currentUser = currentUser;
		this.userService = userService;
		this.modelMapper = modelMapper;
		
	}



	@Override
	public void addHomework(String exercise, String gitAddress) {
		Homework homework = new Homework();
		homework.setGitAddress(gitAddress);
		homework.setAddedOn(LocalDateTime.now());
		homework.setExercise(exerciseService.findByName(exercise));
		System.out.println();
		homework.setAuthor(userService.findById(currentUser.getId()));
		System.out.println();
		homeworkRepository.save(homework);
	}



	@Override
	public HomeworkServiceModel findHomeworkByScore() {
		return homeworkRepository.findTop1ByOrderByComments()
				.map(homework -> modelMapper.map(homework, HomeworkServiceModel.class))
				.orElse(null);
	}



	@Override
	public Homework findById(Long homeworkId) {
		return homeworkRepository.findById(homeworkId).orElse(null);
	}

	
	
}
