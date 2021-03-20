package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

	private Integer score;
	private String textContent;
	private User author;
	private Homework homework;
	
	
	public Comment() {
	
	}

	
	@Column(name = "score")
	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "text_content", columnDefinition = "TEXT")
	public String getTextContent() {
		return textContent;
	}


	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	@ManyToOne
	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}

	@ManyToOne
	public Homework getHomework() {
		return homework;
	}


	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	
	
	
}
