package com.example.demo.services;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.config.ApplicationBeanConfiguration;
import com.example.demo.model.entities.OrderEntity;
import com.example.demo.model.entities.ProductEntity;
import com.example.demo.model.entities.UserEntity;
import com.example.demo.model.services.OrderServiceModel;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.impl.OrderServiceImpl;
import com.example.demo.services.impl.ProductServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private UserRepository userRepository;

	@Before
	public void init() {
		modelMapper = new ModelMapper();
	}
	
	@Test
	public void testGetAllOrders_ByIfPaidFalse() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setIfPaid(false);
		orderEntity.setProducts(List.of(new ProductEntity()));
		
		when(orderRepository.findByIfPaidFalse()).thenReturn(List.of(orderEntity));
		
		List<OrderServiceModel> orders = orderService.getAllOrders();
		
		Assertions.assertEquals(1, orders.size());
		Assertions.assertEquals(false, orders.get(0).isIfPaid());
	
		
	}
	

	@Test
	public void testBuyProducts() {
		OrderEntity orderEntity = new OrderEntity();
		
		UserEntity user = new UserEntity();
		user.setUsername("liudmil");
		user.setPhoneNumber(22522);
		
		userRepository.save(user);
		orderEntity.setUser(user);
		orderEntity.setIfPaid(false);
		
		
		orderRepository.save(orderEntity);
		orderService.buyProducts(user.getUsername());

		boolean expected = true;
		
		boolean actual = orderRepository.findByIfPaidFalse().get(0).isIfPaid();

		Assertions.assertEquals(expected, actual);
		
	/*	//when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
		when(orderRepository.findAllByUserAndIfPaidFalse(user)).thenReturn(List.of(orderEntity));
		
		OrderServiceModel expected = modelMapper.map(orderRepository.findAllByUserAndIfPaidFalse(user).get(0), OrderServiceModel.class);
		OrderServiceModel actual = orderService.buyProducts(user.getUsername());*/

		//Assertions.assertEquals(expected.getId(), actual.getId());
		
	}
}


