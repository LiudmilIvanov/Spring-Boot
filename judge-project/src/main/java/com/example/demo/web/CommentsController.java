package com.example.demo.web;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.CommentAddBindingModel;
import com.example.demo.model.service.CommentServiceModel;
import com.example.demo.model.view.HomeworkViewModel;
import com.example.demo.service.CommentService;
import com.example.demo.service.HomeworkService;

@Controller
@RequestMapping("/comments")
public class CommentsController {

	private final HomeworkService homeworkService;
	private final ModelMapper modelMapper;
	private final CommentService commentService;
	
	@Autowired
	public CommentsController(HomeworkService homeworkService, ModelMapper modelMapper, CommentService commentService) {
		this.homeworkService = homeworkService;
		this.modelMapper = modelMapper;
		this.commentService = commentService;
	}



	@GetMapping("/add")
	public String add(Model model) {
		if (!model.containsAttribute("commentAddBindingModel")) {
			model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
		}
		
		HomeworkViewModel homework = modelMapper.map(homeworkService.findHomeworkByScore(), HomeworkViewModel.class);
		model.addAttribute("homework", homework);
		
		return "homework-check";
	}
	
	@PostMapping("/add")
	public String addConfirm(@Valid CommentAddBindingModel commentAddBindingModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);
		
		return "redirect:/comments/add";
		}
		CommentServiceModel commentServiceModel = modelMapper.map(commentAddBindingModel, CommentServiceModel.class);
		
		commentService.add(commentServiceModel, commentAddBindingModel.getHomeworkId());
		
		
		return "redirect:/";
	}
}
