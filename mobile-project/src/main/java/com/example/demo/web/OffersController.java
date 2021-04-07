package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String addOffer(@Valid @ModelAttribute OfferServiceModel offerModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		
		if (bindingResult.hasErrors()) {
			redirectAttribute.addFlashAttribute("offerModel", offerModel);
			redirectAttribute.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
			System.out.println();
			return "redirect:/offers/add";
		}
		
		
		long newOfferId = offerService.save(offerModel);
		return "redirect:/offers/offer/" + newOfferId;
	}
	
	@GetMapping("/offer/{id}")
	public String offerDetails(@PathVariable String id, Model model) {
		model.addAttribute("id", id);
		
		return "details";
	}
	
	@DeleteMapping("/offer/{id}")
	public String delete(@PathVariable Long id, Model model) {
		//model.addAttribute("id", id);
		offerService.delete(id);
		
		
		return "redirect:/offers/all";
	}
}






