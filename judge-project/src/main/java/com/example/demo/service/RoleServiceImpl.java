package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.enums.RoleName;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}



	@Override
	public void initRoles() {
		if (roleRepository.count() == 0) {
			Role admin = new Role(RoleName.ADMIN);
			Role user = new Role(RoleName.USER);
			
			roleRepository.saveAll(List.of(admin, user));
		}
		
	}

	@Override
	public Role findRole(RoleName roleName) {
		return roleRepository.findByName(roleName)
				.orElseThrow(null);
	}

}
