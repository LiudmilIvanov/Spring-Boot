package com.example.demo.model.view;

import java.util.ArrayList;
import java.util.List;

public class BrandViewModel {

	private String name;

	private List<ModelViewModel> models = new ArrayList<ModelViewModel>();
	
	public List<ModelViewModel> getModels() {
		return models;
	}

	public void setModels(List<ModelViewModel> models) {
		this.models = models;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BrandViewModel() {

	}
	
	public void addModel(ModelViewModel modelViewModel) {
		this.getModels().add(modelViewModel);
	}
	
}
