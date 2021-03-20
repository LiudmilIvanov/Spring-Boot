package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.demo.model.entity.enums.RoleName;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

	
	private RoleName name;

	public Role() {

	}
	
	public Role(RoleName name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
	
	
	
}
