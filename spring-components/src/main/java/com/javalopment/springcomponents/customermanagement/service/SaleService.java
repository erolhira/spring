package com.javalopment.springcomponents.customermanagement.service;

import org.springframework.stereotype.Service;

@Service
public class SaleService implements IService {

	@Override
	public String perform() {
		String result = "SaleService.perform";
		System.out.println(result);
		return result;
	}	
}
