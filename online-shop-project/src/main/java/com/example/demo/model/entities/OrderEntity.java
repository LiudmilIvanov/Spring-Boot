package com.example.demo.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;
	
	@Column(name = "if_paid")
	private boolean ifPaid = false;
	
	@Column
	private BigDecimal totalSum;
	
	@ManyToMany
	@JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "products_id", referencedColumnName = "ID"), 
	inverseJoinColumns = @JoinColumn(name = "orders_id", referencedColumnName = "ID"))
	private List<ProductEntity> products;
	
	@OneToOne
	private UserEntity user;

	
	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public boolean isIfPaid() {
		return ifPaid;
	}

	public void setIfPaid(boolean ifPaid) {
		this.ifPaid = ifPaid;
	}
	
	
	
}
