package com.example.demo.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ExerciseServiceModel {

	private Long id;
	private String name;

	private LocalDateTime startedOn;
	private LocalDateTime dueDate;
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public LocalDateTime getStartedOn() {
		return startedOn;
	}




	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}




	public LocalDateTime getDueDate() {
		return dueDate;
	}




	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}




	public ExerciseServiceModel() {

	}
	
	
}
