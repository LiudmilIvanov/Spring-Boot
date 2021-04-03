package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.binding.ExerciseAddBindingModel;
import com.example.demo.model.service.ExerciseServiceModel;
import com.example.demo.service.ExerciseService;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

	private final ExerciseService exerciseService;
	private final ModelMapper modelMapper;

	
	@Autowired
	public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
		this.exerciseService = exerciseService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/add")
	public String add(Model model) {
		if (!model.containsAttribute("exerciseAddBindingModel")) {
			model.addAttribute("exerciseAddBindingModel", new ExerciseAddBindingModel());
		}
		
		return "exercise-add";
	}
	
	@PostMapping("/add")
	public String addConfirm(@Valid ExerciseAddBindingModel exerciseAddBindingModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
			redirectAttribute.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel", bindingResult);

			return "redirect:add";
		}

		exerciseService.addExercise(modelMapper.map(exerciseAddBindingModel, ExerciseServiceModel.class));
		
		return "redirect:/";
	}
	
}
