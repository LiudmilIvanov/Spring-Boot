package com.example.demo.model.binding;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ExerciseAddBindingModel {

	private String name;
	
	private LocalDateTime startedOn;
	
	private LocalDateTime dueDate;

	@Size(min = 2, message = "Name must be minimum 2 characters!")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@PastOrPresent(message = "Date cannot be in the future!")
	public LocalDateTime getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@FutureOrPresent(message = "Date cannot be in the past!")
	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public ExerciseAddBindingModel() {

	}
	
	
	
	
}
