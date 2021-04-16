package com.example.demo.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.enums.RoleTypeEnum;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	private RoleTypeEnum name;
	
	public RoleTypeEnum getName() {
		return name;
	}

	public void setName(RoleTypeEnum name) {
		this.name = name;
	}

	public RoleEntity(RoleTypeEnum name) {
		this.name = name;
	}

	public RoleEntity() {

	}
	
	
	
	
	
}
