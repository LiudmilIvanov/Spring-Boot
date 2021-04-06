package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.Exercise;
import com.example.demo.model.service.ExerciseServiceModel;

public interface ExerciseService {

	public void addExercise(ExerciseServiceModel exerciseServiceModel);

	public List<String> findAllExNames();

	public boolean checkIfLate(String exercise);

	public Exercise findByName(String name);

}
