package com.example.demo.model.binding;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentAddBindingModel {

	private Integer score;
	private String textContent;
	private Long homeworkId;
	
	@Min(value = 2, message = "Score must be more than 2!")
	@Max(value = 6, message = "Score must not be more than 6!")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	@Size(min = 3, message = "Text content must be more than 3 symbols long!")
	@NotBlank(message = "Cannot be empty!")
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public Long getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(Long homeworkId) {
		this.homeworkId = homeworkId;
	}
	
	
	
}
