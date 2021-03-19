package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Brand;
import com.example.demo.model.entity.Model;
import com.example.demo.model.view.BrandViewModel;
import com.example.demo.model.view.ModelViewModel;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	private final ModelMapper modelMapper;
	private final ModelRepository modelRepository;
	private final BrandRepository brandRepository;
	
	
	@Autowired
	public BrandServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, BrandRepository brandRepository) {
		this.modelMapper = modelMapper;
		this.modelRepository = modelRepository;
		this.brandRepository = brandRepository;
	}



	@Override
	public List<BrandViewModel> getAllBrands() {
		List<BrandViewModel> result = new ArrayList<BrandViewModel>();
		
		List<Model> allModels = this.modelRepository.findAll();
		
		allModels.forEach(m -> {
			Brand brand = m.getBrand();

			//BrandViewModel brandViewModel = this.brandRepository.findByName(brand.getName());
		/*	BrandViewModel brandViewModel = this.modelMapper.map(brand, BrandViewModel.class);
			System.out.println();
			result.add(brandViewModel);*/
			Optional<BrandViewModel> brandViewModelOpt = findByName(result, brand.getName());
				if (!brandViewModelOpt.isPresent()) {
					BrandViewModel newBrandVIewModel = this.modelMapper.map(brand, BrandViewModel.class);
					result.add(newBrandVIewModel);
					brandViewModelOpt = Optional.of(newBrandVIewModel);
				}
				
				ModelViewModel newModelViewModel = this.modelMapper.map(m, ModelViewModel.class);
				brandViewModelOpt.get().addModel(newModelViewModel);
				System.out.println();
				
		});
		
		return result;
	}

	private static Optional<BrandViewModel> findByName(List<BrandViewModel> allModels, String name) {
		return allModels
				.stream()
				.filter(m -> m.getName().equals(name))
				.findAny();
	}
}
