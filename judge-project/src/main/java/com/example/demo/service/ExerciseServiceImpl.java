package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Exercise;
import com.example.demo.model.service.ExerciseServiceModel;
import com.example.demo.repository.ExerciseRepository;

@Service
public class ExerciseServiceImpl implements ExerciseService{

	private final ExerciseRepository exerciseRepository;
	private final ModelMapper modelMapper;
	
	
	@Autowired
	public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
		this.exerciseRepository = exerciseRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public void addExercise(ExerciseServiceModel exerciseServiceModel) {
		Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);
		exercise.setStartedOn(exerciseServiceModel.getStartedOn());
		exercise.setDueDate(exerciseServiceModel.getDueDate());
		
		exerciseRepository.save(exercise);
		
	}

}
