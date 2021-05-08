package com.example.demo.services;

import static org.hamcrest.CoreMatchers.any;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.config.ApplicationBeanConfiguration;
import com.example.demo.model.binding.ProductAddBindingModel;
import com.example.demo.model.entities.CategoryEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.enums.CategoryTypeEnum;
import com.example.demo.model.services.ProductServiceModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.impl.ProductServiceImpl;

@SpringBootTest(classes = ProductServiceImpl.class)
@ContextConfiguration(classes = {ApplicationBeanConfiguration.class})
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	@Autowired
	private ModelMapper modelMapper;

	@Before
	public void init() {
		modelMapper = new ModelMapper();
	}
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private CategoryRepository categoryRepository;

	@Test
	void testFindById() {
		ProductEntity product = new ProductEntity();
		product.setId(15);
		
		when(productRepository.findById(15L)).thenReturn(Optional.of(product));

		ProductServiceModel productEntity = productService.findById(15L);
		
		Assertions.assertEquals(15, productEntity.getId());
		
	
	}
	
	@Test
	public void testFindAllByName() {
		ProductEntity product = new ProductEntity();
		product.setName("Minth");
		
		when(productRepository.findAllByName(product.getName())).thenReturn(List.of(product));
		
		List<ProductServiceModel> productEntities = productService.findAllByName(product.getName());
		
		Assertions.assertEquals(1, productEntities.size());
		Assertions.assertEquals("Minth", productEntities.get(0).getName());
		
	}
	
	
	@Test
	public void testAddProducts() {
		ProductServiceModel product = new ProductServiceModel();
		product.setName("GreenTea");
		product.setCategory(new CategoryEntity(CategoryTypeEnum.TEA));
		product.setId(15L);
		product.setDescription("description");
		product.setImageUrl("link");
		product.setQuantity(1);
		product.setPrice(new BigDecimal(2));
		
		when(productRepository.findAll()).thenReturn(List.of(modelMapper.map(product, ProductEntity.class)));
		
		
		ProductServiceModel expected = modelMapper.map(productRepository.findAll().get(0), ProductServiceModel.class);
		ProductServiceModel actual = productService.addProduct(modelMapper.map(product, ProductAddBindingModel.class));

		Assertions.assertEquals(expected.getName(), actual.getName());
		
	}
	
}
