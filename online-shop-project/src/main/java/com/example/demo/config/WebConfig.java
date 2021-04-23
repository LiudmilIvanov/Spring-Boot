package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.stats.StatsInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer{

	private final StatsInterceptor statsInterceptor;
	
	@Autowired
	public WebConfig(StatsInterceptor statsInterceptor) {
		this.statsInterceptor = statsInterceptor;
	}



	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(statsInterceptor);
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
