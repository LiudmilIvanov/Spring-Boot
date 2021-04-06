package com.example.demo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity{

	private String name;
	private LocalDateTime startedOn;
	private LocalDateTime dueDate;
	
	public Exercise() {

	}
	@Column(name = "name",unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "started_on")
	public LocalDateTime getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}
	@Column(name = "due_date")
	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	
	

	
	
}
