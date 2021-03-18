package com.example.demo.entities;

import javax.persistence.Table;

import com.example.demo.entities.enums.EngineType;
import com.example.demo.entities.enums.TransmissionType;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{

	@Enumerated(EnumType.STRING)
	private EngineType engine;
	
	@Column
	private String description;
	
	@Column
	private String imageUrl;
	
	@Column
	private int mileage;
	
	@Column
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private TransmissionType transmission;

	@Column
	private int year;
	
	@ManyToOne
	private Model model;
	
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Offer() {

	}
	
	
}
