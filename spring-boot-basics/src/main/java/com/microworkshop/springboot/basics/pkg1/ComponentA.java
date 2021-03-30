package com.microworkshop.springboot.basics.pkg1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.microworkshop.springboot.basics.MyTeam;

import lombok.Getter;
import lombok.Setter;

@Component
public class ComponentA {
	
	@Autowired
	private Environment env;
	
	/*
	 * value from override.properties
	 * override.properties is set in command parameter as follows:
	 * --spring.config.location="file:D:/dev/ws2/ws_spring_boot/externalized.properties",classpath:/override.properties
	 */
	@Getter @Setter
	@Value("${component.A.name}")
	private String name;
	
	/*
	 * value from command param as follows:
	 * --param.from.command.line="This is from command line.."
	 */
	@Getter @Setter
	@Value("${param.from.command.line}")
	private String fromCommandLine;
	
	@Getter @Setter
	@Value("${component.A.run}")
	private String run;	
	
	@Autowired @Getter @Setter
	private MyTeam myTeam;
	
	@Getter @Setter
	@Value("${which.environment}")
	private String environment;
	
	@Getter @Setter
	@Value("${param.propCustom}")
	private String propCustom;	
	
	private String propCustomFromArguments;
	
	@Autowired
	public ComponentA(ApplicationArguments args) {
		boolean debug = args.containsOption("debug");
		List<String> files = args.getNonOptionArgs();
		
		//environment is null here, initialized after instance is initialized.
		System.out.println("debug --> " + debug + " -- environment: " + environment);
		
		if(args.containsOption("param.propCustom")) {			
			propCustomFromArguments = args.getOptionValues("param.propCustom").stream().findFirst().orElse(null);
		}
	}
	
	public String getNameFromEnvironment() {
		return env.getProperty("component.A.name");
	}
	
	public String getPropCustomFromEnvironment() {
		return env.getProperty("param.propCustom");
	}
	
	public String getPropCustomFromInjectedValue() {
		return propCustom;
	}
	
	public String getPropCustomFromSystem() {
		return System.getProperty("param.propCustom");
	}
	
	public String getPropCustomFromArguments() {
		return propCustomFromArguments;
	}
}
