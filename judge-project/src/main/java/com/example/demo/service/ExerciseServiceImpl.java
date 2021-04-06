package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

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



	@Override
	public List<String> findAllExNames() {
		return exerciseRepository.findAllExerciseNames();
	}



	@Override
	public boolean checkIfLate(String exercise) {
		Exercise exerciseEntity = exerciseRepository.findByName(exercise)
			.orElse(null); 
		
		return exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
	}



	@Override
	public Exercise findByName(String name) {
		
		return exerciseRepository
				.findByName(name).orElse(null);
	}

}
