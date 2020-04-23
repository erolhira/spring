package com.javalopment.springcomponents.customermanagement.service;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class StateService implements IService {

	@Getter @Setter
	private String serviceName = "customerService";
	
	@Override
	public String perform() {
		String result = "StatisticsService.perform";
		System.out.println(result);
		return result;
	}

}
