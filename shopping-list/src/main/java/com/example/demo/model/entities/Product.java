package com.example.demo.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

	@Column(unique = true)
	@Length(min = 3, max = 20)
	private String name;
	
	@Column
	@Length(min = 5)
	private String description;

	@Column
	@DecimalMin(value = "0.0")
	private BigDecimal price;

	@Column(name = "needed_before")
	@FutureOrPresent(message = "Date cannot be in the past!")
	private LocalDateTime neededBefore;
	
	@ManyToOne
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getNeededBefore() {
		return neededBefore;
	}

	public void setNeededBefore(LocalDateTime neededBefore) {
		this.neededBefore = neededBefore;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
