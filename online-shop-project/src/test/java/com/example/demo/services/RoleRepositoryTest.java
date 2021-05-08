package com.example.demo.services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.repository.RoleRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RoleRepositoryTest {

	@Autowired
	private RoleService roleService;
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Test
	public void testFindByName() {
		RoleEntity role = new RoleEntity();
		role.setName(RoleTypeEnum.ROLE_ADMIN);
		role.setId(3);
		
		when(roleRepository.findByName(RoleTypeEnum.ROLE_ADMIN)).thenReturn(role);
		
		RoleEntity roleEntity = roleRepository.findByName(RoleTypeEnum.ROLE_ADMIN);
		
		Assertions.assertEquals(3, role.getId());
		Assertions.assertEquals(RoleTypeEnum.ROLE_ADMIN, roleEntity.getName());
	
	}
	
}
