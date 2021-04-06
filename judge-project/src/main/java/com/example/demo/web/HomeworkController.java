package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.HomeworkAddBindingModel;
import com.example.demo.service.ExerciseService;
import com.example.demo.service.HomeworkService;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

	private final ExerciseService exerciseService;
	private final HomeworkService homeworkService;
	
	
	@Autowired
	public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService) {
		this.exerciseService = exerciseService;
		this.homeworkService = homeworkService;
	}



	@GetMapping("/add")
	public String addHomework(Model model) {
		if (!model.containsAttribute("homeworkAddBindingModel")) {
			model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
			model.addAttribute("isLate", false);
		}
		
		model.addAttribute("exNames", exerciseService.findAllExNames());
		
		return "homework-add";
	}
	
	@PostMapping("/add")
	public String addConfirm(@Valid HomeworkAddBindingModel homeworkAddBindingModel, BindingResult bindingResult,
			RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
			redirectAttribute.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);
	
		return "redirect:add";
		}
		
		boolean isLate = exerciseService.checkIfLate(homeworkAddBindingModel.getExercise());
		
		if (isLate) {
			redirectAttribute.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
			redirectAttribute.addFlashAttribute("isLate", true);
		}
		
		homeworkService.addHomework(homeworkAddBindingModel.getExercise(), homeworkAddBindingModel.getGitAddress());
		
		return "redirect:/";
	}
	
}
