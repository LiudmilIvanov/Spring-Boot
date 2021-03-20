package com.example.demo.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "homework")
public class Homework extends BaseEntity{

	private LocalDateTime addedOn;
	private String gitAddress;
	private User author;
	private Exercise exercise;
	
	public Homework() {
		
	}

	@Column(name = "added_on")
	public LocalDateTime getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(LocalDateTime addedOn) {
		this.addedOn = addedOn;
	}
	
	@Column(name = "git_address", unique = true)
	public String getGitAddress() {
		return gitAddress;
	}
	
	public void setGitAddress(String gitAddress) {
		this.gitAddress = gitAddress;
	}
	
	@ManyToOne	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

	@ManyToOne
	public Exercise getExercise() {
		return exercise;
	}
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	
	
	
}
