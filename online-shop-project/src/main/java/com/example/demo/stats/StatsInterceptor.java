package com.example.demo.stats;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class StatsInterceptor implements HandlerInterceptor{

	private final StatsService statsService;
	
	
	@Autowired
	public StatsInterceptor(StatsService statsService) {
		this.statsService = statsService;
	}



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		statsService.increaseRequestCount();
		
		return true;
	}
	
}
