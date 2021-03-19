package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BrandService;

@Controller
@RequestMapping("/brands")
public class BrandsController {

	private final BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}


	@GetMapping("/all")
	public String allBrands(Model model) {
		model.addAttribute("brands", brandService.getAllBrands());
		return "brands";
	}
}
