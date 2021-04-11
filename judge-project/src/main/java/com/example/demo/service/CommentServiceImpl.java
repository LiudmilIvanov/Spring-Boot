package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Comment;
import com.example.demo.model.service.CommentServiceModel;
import com.example.demo.repository.CommentRepository;
import com.example.demo.security.CurrentUser;

@Service
public class CommentServiceImpl implements CommentService{
	
	private final CommentRepository commentRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final CurrentUser currentUser;
	private final HomeworkService homeworkService;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, HomeworkService homeworkService) {
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.currentUser = currentUser;
		this.homeworkService = homeworkService;
	}



	@Override
	public void add(CommentServiceModel commentServiceModel, Long homeworkId) {
		Comment comment = modelMapper.map(commentServiceModel, Comment.class);
		comment.setAuthor(userService.findById(currentUser.getId()));
		comment.setHomework(homeworkService.findById(homeworkId));
		
		commentRepository.save(comment);
		
	}

}
