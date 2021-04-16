package com.example.demo.services;

import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.RoleTypeEnum;

public interface RoleService {

	public boolean ifRolesExist();
	
	public void saveRoles(RoleEntity role);
	
	public RoleEntity findByName(RoleTypeEnum name);
}
