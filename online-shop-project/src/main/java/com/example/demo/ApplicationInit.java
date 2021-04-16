package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.services.RoleService;

@Component
public class ApplicationInit implements CommandLineRunner{

	private final RoleService roleService;
	
	
	@Autowired
	public ApplicationInit(RoleService roleService) {
		this.roleService = roleService;
	}



	@Override
	public void run(String... args) throws Exception {
		intRoles();
	}



	private void intRoles() {
		if (!roleService.ifRolesExist()) {
			Arrays.stream(RoleTypeEnum.values())
				.forEach(roleName -> {
					RoleEntity role = new RoleEntity(roleName);
					
					roleService.saveRoles(role);
				});
		}
		
	}

	
}
