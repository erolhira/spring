package com.microworkshop.springboot.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.microworkshop.springboot.basics.pkg1.ComponentA;

/*
 * @SpringBootApplication is a top-level annotation that encapsulates the following annotations:
 * @Configuration: hints that the contained class declares one or more @Bean definitons. 
 * @EnableAutoConfiguration: tells Spring Boot to automatically configure the Spring application 
 * based on the dependencies available in the classpath.
 * @ComponentScan
 * 
 * https://dzone.com/articles/spring-component-scan
 * 
 * If you need to find out what auto-configuration is currently being applied, and why, start your application with the --debug switch
 * 
 * The @ComponentScan annotation is used with the @Configuration annotation to tell Spring the packages to scan for annotated components.
 * When using Spring Boot, most of the time, the default auto scanning will work for your project. 
 * You only need to ensure that your @SpringBoot main class is at the base package of your package hierarchy. 
 * Spring Boot will automatically perform a component scan in the package of the Spring Boot main class and below.
 */
//@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude= {JmxAutoConfiguration.class, SpringApplicationAdminJmxAutoConfiguration.class})
@ComponentScan//(basePackages= { "com.microworkshop" })
//@Import({ComponentA.class, ComponentB.class}) //can be used instead of @ComponentScan
public class Sample0SpringBootBasicsApplication {	
	
	private static final Logger loggerDefault = LoggerFactory.getLogger("sample.logback");
	private static final Logger loggerApp = LoggerFactory.getLogger("app.log");
	
	ComponentA component;
	
	@Autowired
	ComponentA componentA2;
	
	@Autowired @Qualifier("componentA")
	ComponentA componentA3;
	
	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(Sample0SpringBootBasicsApplication.class, args);
		
		System.out.println("Contains A --> " + context.containsBean("componentA"));
		System.out.println("Contains A2 --> " + context.containsBean("componentA2"));
		System.out.println("Contains A3 --> " + context.containsBean("componentA3"));
		System.out.println("Contains B --> " + context.containsBean("componentB"));
		System.out.println("Contains component --> " + context.containsBean("component"));
		
		Sample0SpringBootBasicsApplication app = context.getBean(Sample0SpringBootBasicsApplication.class);
		System.out.println("app.component == app.componentA2 --> " + (app.component == app.componentA2));
		System.out.println("app.componentA2 == app.componentA3 --> " + (app.componentA2 == app.componentA3));				
		
		System.out.println("----------------------------------------------------");
		
		ComponentA componentA = context.getBean(ComponentA.class);
		System.out.println(componentA.getName());
		System.out.println("name from environment: " + componentA.getName());
		System.out.println("getNameFromEnvironment: " + componentA.getNameFromEnvironment());
		System.out.println(componentA.getFromCommandLine());
		System.out.println("componentA environment: " + componentA.getEnvironment());
		System.out.println("run --> " + componentA.getRun());
		System.out.println("Team --> " + componentA.getMyTeam().getName());
		System.out.println("Players --> " + componentA.getMyTeam().getPlayers());
		System.out.println("Statistics --> " + componentA.getMyTeam().getStatistics());
		System.out.println("WinMatch --> " + componentA.getMyTeam().getStatistics().get("win-match"));
		System.out.println("----------------------------------------------------");
		
		System.out.println("Contains C --> " + context.containsBean("componentC") + " - since dev is active profile");
		System.out.println("Contains D --> " + context.containsBean("componentD") + " - since prod is not active profile");
		
		System.out.println("whichEnvironment --> " + componentA.getEnvironment());
		System.out.println("----------------------------------------- ");
		System.out.println("PropCustomFromArguments: " + componentA.getPropCustomFromArguments());
		System.out.println("PropCustomFromEnvironment: " + componentA.getPropCustomFromEnvironment());
		System.out.println("PropCustomFromInjectedValue: " + componentA.getPropCustomFromInjectedValue());
		System.out.println("PropCustomFromSystem: " + componentA.getPropCustomFromSystem());
		
		loggerDefault.debug("spring boot demo ends.");
		loggerApp.debug("spring boot demo ends.");
	}
	
	
}
