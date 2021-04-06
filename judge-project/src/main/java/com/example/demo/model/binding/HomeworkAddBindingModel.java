package com.example.demo.model.binding;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class HomeworkAddBindingModel {

	private String exercise;
	private String gitAddress;
	
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	//@Pattern(regexp = "\\/\\/github\\.com\\/.+", message = "Enter valid github address!")
	public String getGitAddress() {
		return gitAddress;
	}
	public void setGitAddress(String gitAddress) {
		this.gitAddress = gitAddress;
	}
	
	public HomeworkAddBindingModel() {

	}

	
	
}
