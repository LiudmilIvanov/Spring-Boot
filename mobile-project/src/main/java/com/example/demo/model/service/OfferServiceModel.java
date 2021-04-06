package com.example.demo.model.service;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.example.demo.model.entity.Model;
import com.example.demo.model.entity.enums.EngineType;
import com.example.demo.model.entity.enums.TransmissionType;
import com.example.demo.model.validaiton.YearInPastOrPresent;

public class OfferServiceModel {

	@NotNull
	private EngineType engine;
	@NotNull
	private String imageUrl;
	@NotNull
	@Positive
	private Integer mileage;
	
	@DecimalMin("100")
	private BigDecimal price;
	
	@YearInPastOrPresent(minYear = 1930)
	private Integer year;
	
	@NotEmpty
	@Size(min = 10, max = 512)
	private String description;
	
	@NotNull
	private TransmissionType transmission;
	
	@NotNull
	private Integer modelId;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getMileage() {
		return mileage;
	}
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public EngineType getEngine() {
		return engine;
	}
	public void setEngine(EngineType engine) {
		this.engine = engine;
	}
	public TransmissionType getTransmission() {
		return transmission;
	}
	public void setTransmission(TransmissionType transmission) {
		this.transmission = transmission;
	}
	
	
	
}
