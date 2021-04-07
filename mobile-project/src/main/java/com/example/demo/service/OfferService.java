package com.example.demo.service;

import java.util.List;

import com.example.demo.model.service.OfferServiceModel;
import com.example.demo.model.view.OfferSummaryViewModel;

public interface OfferService {

	public List<OfferSummaryViewModel> getAllOffers();
	
	public long save(OfferServiceModel model);
	
	void delete(long id);
}
