package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.ExerciseAddBindingModel;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

	@GetMapping("/add")
	public String add() {
		return "exercise-add";
	}
	
	@PostMapping("/add")
	public String addConfirm(@Valid ExerciseAddBindingModel exerciseAddBindingModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		
		
		
		return "redirect:/";
	}
	
}
