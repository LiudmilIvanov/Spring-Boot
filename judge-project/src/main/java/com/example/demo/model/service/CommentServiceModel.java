package com.example.demo.model.service;

public class CommentServiceModel {

	private Integer score;
	private String textContent;
	private UserServiceModel author;
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public UserServiceModel getAuthor() {
		return author;
	}
	public void setAuthor(UserServiceModel author) {
		this.author = author;
	}
	
	
}
