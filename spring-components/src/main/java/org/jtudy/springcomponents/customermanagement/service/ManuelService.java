package org.jtudy.springcomponents.customermanagement.service;

public class ManuelService implements IService {

	@Override
	public String perform() {
		String result = "ManuelService.perform";
		System.out.println(result);
		return result;
	}

}
