package com.example.demo.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.ProductServiceModel;
import com.example.demo.services.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private ModelMapper modelMapper;
	
	private ProductService productService = Mockito.mock(ProductService.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private HomeController controller;
	
	@Test
	public void contextLoad() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void testHomePage() throws Exception {
		ProductServiceModel productServiceModel = new ProductServiceModel();
		productServiceModel.setId(15L);
		productServiceModel.setName("tea");
		productServiceModel.setImageUrl("asdasd");
		productServiceModel.setDescription("adasdasdadasdsa");
		productServiceModel.setCategory(new CategoryEntity(CategoryTypeEnum.TEA));
		
		when(productService.getRandomProduct()).thenReturn(productServiceModel);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/home"))
		.andExpect(status()
				.isOk())
				.andExpect(model().attribute("productOne", productServiceModel));
	}
}
