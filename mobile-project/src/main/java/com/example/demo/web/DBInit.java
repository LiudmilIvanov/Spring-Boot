package com.example.demo.web;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Brand;
import com.example.demo.model.entity.Model;
import com.example.demo.model.entity.Offer;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.enums.EngineType;
import com.example.demo.model.entity.enums.ModelCategory;
import com.example.demo.model.entity.enums.TransmissionType;
import com.example.demo.model.entity.enums.UserRoleEnum;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.repositories.OfferRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;

@Component
public class DBInit implements CommandLineRunner{

	private final ModelRepository modelRepository;
	private final BrandRepository brandRepository;
	private final OfferRepository offerRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Autowired
	public DBInit(ModelRepository modelRepository, BrandRepository brandRepository, 
			OfferRepository offerRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, 
			UserRoleRepository userRoleRepositor) {
		this.modelRepository = modelRepository;
		this.brandRepository = brandRepository;
		this.offerRepository = offerRepository;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepositor;
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		Brand fordBrand = new Brand();
		fordBrand.setName("Ford");
	//	fordBrand.setCreated(Instant.now());
	//	fordBrand.setUpdated(Instant.now());
		
		Model fiesta = initFiest(fordBrand);
		Model escort = initEscort(fordBrand);
		
		Brand hondaBrand = new Brand();
		hondaBrand.setName("Honda");
	//	hondaBrand.setCreated(Instant.now());
	//	hondaBrand.setUpdated(Instant.now());
		
		Model honda = initNC750S(hondaBrand);
		
		brandRepository.saveAll(List.of(fordBrand, hondaBrand));
		
		Model fiestaModel = initFiest(fordBrand);
		createFiestaOffer(fiestaModel);
		
		initUsers();
	}
	
	private void initUsers() {
		UserRole adminRole = new UserRole(); 
		adminRole.setRole(UserRoleEnum.ADMIN);
		
		UserRole userRole = new UserRole();
		userRole.setRole(UserRoleEnum.USER);
		
		userRoleRepository.saveAll(List.of(adminRole, userRole));
		System.out.println();
		User admin = new User();
		admin.setFirstName("Kiril");
		admin.setLastName("Dimitrov");
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("topsecret"));
		//admin.setCreated(Instant.now());
		//admin.setUpdated(Instant.now());
		admin.setUserRoles(List.of(adminRole, userRole));
		
		User user = new User();
		user.setFirstName("Peter");
		user.setLastName("Ivanov");
		user.setUsername("pesho123");
		user.setPassword(passwordEncoder.encode("topsecret"));
	//	user.setCreated(Instant.now());
		/*	user.setUpdated(Instant.now());*/
		user.setUserRoles(List.of(userRole));
		
		userRepository.saveAll(List.of(admin, user));
	
	}
	
	private void createFiestaOffer(Model model) {
		Offer fiestaOffer = new Offer();
		fiestaOffer.setEngine(EngineType.GASOLINE);
		fiestaOffer.setImageUrl("https://www.motopfohe.bg/files/news/archive/2017/08/blob-server2.jpg");
		fiestaOffer.setMileage(80000);
		fiestaOffer.setPrice(BigDecimal.valueOf(10000));
		fiestaOffer.setYear(2019);
		fiestaOffer.setDescription("Brought from Germany.");
		fiestaOffer.setTransmission(TransmissionType.MANUAL);
		fiestaOffer.setModel(model);
		/*	fiestaOffer.setCreated(Instant.now());
		fiestaOffer.setUpdated(Instant.now());*/
		
		this.offerRepository.save(fiestaOffer);
		
	}

	private Model initNC750S(Brand hondaBrand) {
		Model nc750s = new Model();
		nc750s.setName("NC750S");
		nc750s.setCategory(ModelCategory.MOTORCYCLE);
		nc750s.setImageUrl("https://www.bultraco-sofia.bg/sites/default/files/color_01_15.jpg");
		nc750s.setStartYear(2014);
		nc750s.setBrand(hondaBrand);
		/*	nc750s.setCreated(Instant.now());
		nc750s.setUpdated(Instant.now());*/
		
		return this.modelRepository.save(nc750s);
	}
	
	private Model initEscort(Brand fordBrand) {
		Model escort = new Model();
		escort.setName("Fiesta");
		escort.setCategory(ModelCategory.CAR);
		escort.setImageUrl("https://autobild.bg/wp-content/uploads/2019/06/Ford-Escort-Cosworth-Klassiker-des-Tages-560x373-242961fa50ea5552.jpg");
		escort.setStartYear(1968);
		escort.setEndYear(2002);
		escort.setBrand(fordBrand);
		/*	escort.setCreated(Instant.now());
		escort.setUpdated(Instant.now());*/
		
		return this.modelRepository.save(escort);
	}

	private Model initFiest(Brand fordBrand) {
		Model fiesta = new Model();
		fiesta.setName("Fiesta");
		fiesta.setCategory(ModelCategory.CAR);
		fiesta.setImageUrl("https://www.motopfohe.bg/files/news/archive/2017/08/blob-server2.jpg");
		fiesta.setStartYear(1976);
		fiesta.setBrand(fordBrand);
	/*	fiesta.setCreated(Instant.now());
		fiesta.setUpdated(Instant.now());*/
		
		return this.modelRepository.save(fiesta);
	}

	
}
