package com.example.demo.model.view;

import java.math.BigDecimal;

import com.example.demo.model.entity.enums.EngineType;
import com.example.demo.model.entity.enums.TransmissionType;

public class OfferSummaryViewModel {

	private EngineType engine;
	
	private String imageUrl;

	private int mileage;
	
	private BigDecimal price;
	
	private TransmissionType transmission;

	
	
	public OfferSummaryViewModel() {

	}

	public EngineType getEngine() {
		return engine;
	}

	public void setEngine(EngineType engine) {
		this.engine = engine;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public TransmissionType getTransmission() {
		return transmission;
	}

	public void setTransmission(TransmissionType transmission) {
		this.transmission = transmission;
	}
	
	
	
}
