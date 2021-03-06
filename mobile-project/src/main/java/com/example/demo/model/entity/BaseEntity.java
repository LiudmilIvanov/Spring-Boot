package com.example.demo.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private Instant created;
	
	@Column(nullable = false)
	private Instant updated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@PrePersist
	public void prePersist() {
		setCreated(Instant.now());
		setUpdated(Instant.now());
	}
	
	@PreUpdate
	public void preUpdate() {
		setUpdated(Instant.now());
	}
	
	
	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getUpdated() {
		return updated;
	}

	public void setUpdated(Instant updated) {
		this.updated = updated;
	}

	public BaseEntity() {

	}
	
	
}
