package com.example.demo;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.RoleService;

@Component
public class ApplicationInit implements CommandLineRunner{

	private final RoleService roleService;
	private final ProductRepository productRepository;
	
	
	@Autowired
	public ApplicationInit(RoleService roleService, ProductRepository productRepository) {
		this.roleService = roleService;
		this.productRepository = productRepository;
	}



	@Override
	public void run(String... args) throws Exception {
	/*	intRoles();
		ProductEntity product = new ProductEntity();
		product.setName("Black tea");
		product.setImageUrl("https://teahousesofia.com/assets/teas/_51B3423_resized.jpg");
		product.setPrice(new BigDecimal(2));
		
		
		productRepository.save(product);*/
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
