package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.security.CurrentUser;
import com.example.demo.service.CommentService;
import com.example.demo.service.ExerciseService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	private final CurrentUser currentUser;
	private final ExerciseService exerciseService;
	private final CommentService commentService;
	private final UserService userService;
	
	@Autowired
	public HomeController(CurrentUser currentUser, ExerciseService exerciseService, CommentService commentService, UserService userService) {
		this.currentUser = currentUser;
		this.exerciseService = exerciseService;
		this.commentService = commentService;
		this.userService = userService;
	}



	@GetMapping("/")
	public String index(Model model) {
		if (currentUser.isAnonymous()) {
			return "index";
		}
		model.addAttribute("exercises", exerciseService.findAllExNames());
		model.addAttribute("avg", commentService.findAvgScore());
		model.addAttribute("userCount", userService.findUserCount());
		model.addAttribute("scoreMap", commentService.findScoreMap());
		
		
		return "home";
	}
	
}
