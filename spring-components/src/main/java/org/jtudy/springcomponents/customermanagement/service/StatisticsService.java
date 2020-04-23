package org.jtudy.springcomponents.customermanagement.service;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class StatisticsService implements IService {

	@Getter @Setter
	private int hitCount;
	
	@Override
	public String perform() {
		String result = "StatisticsService.perform";
		System.out.println(result);
		return result;
	}

	
}
