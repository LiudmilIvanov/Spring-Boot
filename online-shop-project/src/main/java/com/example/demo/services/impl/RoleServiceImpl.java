package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}



	@Override
	public boolean ifRolesExist() {
		return roleRepository.count() > 0;
	}



	@Override
	public void saveRoles(RoleEntity role) {
		roleRepository.save(role);
	}



	@Override
	public RoleEntity findByName(RoleTypeEnum name) {
		return roleRepository.findByName(name);
	}

}
