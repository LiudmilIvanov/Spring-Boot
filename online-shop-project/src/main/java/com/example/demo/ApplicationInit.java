package com.example.demo;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.entities.RoleEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.enums.RoleTypeEnum;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.OrderService;
import com.example.demo.services.RoleService;

@Component
public class ApplicationInit implements CommandLineRunner{

	private final RoleService roleService;
	private final ProductRepository productRepository;
	private final CategoryService categoryService;
	private final OrderService orderService;
	
	
	@Autowired
	public ApplicationInit(RoleService roleService, ProductRepository productRepository, CategoryService categoryService, OrderService orderService) {
		this.roleService = roleService;
		this.productRepository = productRepository;
		this.categoryService = categoryService;
		this.orderService = orderService;
	}



	@Override
	public void run(String... args) throws Exception {
		intRoles();
		initCategories();
		//deleteOrders();
		//productRepository.deleteAll();
	/*	ProductEntity product = new ProductEntity();
		product.setName("Black tea");
		product.setImageUrl("https://teahousesofia.com/assets/teas/_51B3423_resized.jpg");
		product.setPrice(new BigDecimal(2));
		
		
		productRepository.save(product);*/
	}



	private void deleteOrders() {
		orderService.delete();
	}



	private void initCategories() {
		if (!categoryService.ifCategoryExist()) {
			Arrays.stream(CategoryTypeEnum.values())
				.forEach(categoryName -> {
					CategoryEntity category = new CategoryEntity(categoryName);
					
					categoryService.saveCategories(category);
				});
		}
		
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
