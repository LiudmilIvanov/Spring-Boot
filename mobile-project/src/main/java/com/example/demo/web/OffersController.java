package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OffersController {

	private final OfferService offerService;
	
	@Autowired
	public OffersController(OfferService offerService) {
		this.offerService = offerService;
	}


	@GetMapping("/all")
	public String getAllOffers(Model model) {
		model.addAttribute("models", offerService.getAllOffers());
		return "offers";
	}
	
}
