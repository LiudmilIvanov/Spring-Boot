package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Offer;
import com.example.demo.model.service.OfferServiceModel;
import com.example.demo.model.view.OfferSummaryViewModel;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.repositories.OfferRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService{

	private final ModelMapper modelMapper;
	private final OfferRepository offerRepository;
	private final CurrentUser currentUser;
	private final UserRepository userRepository;
	private final ModelRepository modelRepository;
	
	@Autowired
	public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, CurrentUser currentUser,
			UserRepository userRepository, ModelRepository modelRepository) {
		this.modelMapper = modelMapper;
		this.offerRepository = offerRepository;
		this.currentUser = currentUser;
		this.userRepository = userRepository;
		this.modelRepository = modelRepository;
	}

	@Override
	public List<OfferSummaryViewModel> getAllOffers() {
		//TODO
		
		return null;
	}

	@Override
	public long save(OfferServiceModel model) {
		Offer offer = asNewEntity(model);
		System.out.println();
		Offer offerNewEntity = offerRepository.save(offer);
		return offerNewEntity.getId();
	}
	
	private Offer asNewEntity(OfferServiceModel model) {
		Offer offer = modelMapper.map(model, Offer.class);
		offer.setCreated(Instant.now());
		offer.setModel(modelRepository.findById(model.getModelId()).orElseThrow());
		offer.setUser(userRepository.findByUsername(currentUser.getName()).orElseThrow());
		
		
		return offer;
	}

	@Override
	public void delete(long id) {
		offerRepository.deleteById(id);
		
	}
	
}
