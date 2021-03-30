package com.microworkshop.springboot.basics;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="team")
@Component
public class MyTeam {

	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String winMatch;
	
	@Getter @Setter
	private List<String> players;
	
	@Getter @Setter
	private Map<String, String> statistics;
}
