package com.example.demo.services;

import com.example.demo.model.entities.RoleEntity;

public interface RoleService {

	public boolean ifRolesExist();
	
	public void saveRoles(RoleEntity role);
}
