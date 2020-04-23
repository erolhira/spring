package com.javalopment.springcomponents.customermanagement.service;

import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService, IService {

	public String greetCustomer(String customerName) {
		return "Hello " + customerName;
	}
	
	@Override
	public String perform() {
		String result = "CustomerService.perform";
		System.out.println(result);
		return result;
	}
}
