package com.example.demo.service;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.enums.RoleName;

public interface RoleService {
	void initRoles();
	
	Role findRole(RoleName roleName);
}
