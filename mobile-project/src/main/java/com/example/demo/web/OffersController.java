package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.entity.enums.EngineType;
import com.example.demo.model.entity.enums.TransmissionType;
import com.example.demo.model.service.OfferServiceModel;
import com.example.demo.service.BrandService;
import com.example.demo.service.OfferService;

import net.bytebuddy.build.Plugin.Engine;

@Controller
@RequestMapping("/offers")
public class OffersController {

	private final OfferService offerService;
	private final BrandService brandService;
	
	
	
	
	@Autowired
	public OffersController(OfferService offerService, BrandService brandService) {
		this.offerService = offerService;
		this.brandService = brandService;
	}


	@ModelAttribute("offerServiceModel")
	public OfferServiceModel offerModel() {
		return new OfferServiceModel();
	}
	
	@GetMapping("/all")
	public String getAllOffers(Model model) {
		model.addAttribute("models", offerService.getAllOffers());
		return "offers";
	}
	
	@GetMapping("/add")
	public String newOffer(Model model) {
		model.addAttribute("brands", brandService.getAllBrands());
		model.addAttribute("engines", EngineType.values());
		model.addAttribute("transmissions", TransmissionType.values());
		
		return "offer-add";
	}
	
	@PostMapping("/add")
	public String addOffer(@ModelAttribute OfferServiceModel offerModel) {
		System.out.println();
		return "redirect:/offers/all";
	}
	
}
