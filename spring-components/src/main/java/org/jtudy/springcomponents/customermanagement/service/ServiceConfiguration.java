package org.jtudy.springcomponents.customermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfiguration {
	
	@Autowired @Qualifier("customerService")
	private IService customerService;
	
	@Autowired @Qualifier("saleService")
	private IService saleService;	
	
	@Autowired
	private StateService stateService;
	
	@Bean(name = "dynamicService")
	@Scope("prototype")
	public IService dynamicService() {
		IService service = null;
		String serviceName = stateService.getServiceName();
		if("customerService".equals(serviceName)) {
			service = customerService;
		} else if("saleService".equals(serviceName)) {
			service = saleService;
		} else if("manuelService".equals(serviceName)) {
			service = new ManuelService();
		}
		return service;
	};
}
