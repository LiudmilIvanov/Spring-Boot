package com.example.demo.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

	@Column(unique = true)
	@Size(min = 3, max = 20)
	private String name;
	
	@Column(nullable = false)
	@DecimalMin(value = "0")
	private BigDecimal price;
	
	@Column(nullable = false)
	@Min(value = 0)
	private long quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	
}
