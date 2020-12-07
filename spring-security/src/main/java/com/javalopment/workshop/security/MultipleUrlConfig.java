package com.javalopment.workshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(value = 103)
public class MultipleUrlConfig extends WebSecurityConfigurerAdapter {

	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.requestMatchers()
 				.antMatchers(HttpMethod.GET, "/web/**")
                .antMatchers(HttpMethod.POST, "/web/**")
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin(); 		 		 		
 	}
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    
    	auth
    		.inMemoryAuthentication()
      			.withUser("test_usr").password(encoder.encode("test_passw")).roles("USER")
      			.and()
      			.withUser("admin_usr").password(encoder.encode("admin_passw")).roles("USER", "ADMIN");
    }
}
