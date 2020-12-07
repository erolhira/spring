package com.javalopment.workshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration 
@Order(value = 101)
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.headers().contentSecurityPolicy("script-src 'self'");
        //http.csrf().disable();		
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.antMatcher("/api/**")
        	.authorizeRequests()
                .antMatchers("/api/order/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().httpBasic();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	        .antMatchers("/register", "/api/public/**");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    
    	auth.inMemoryAuthentication()
      			.withUser("test_usr").password(encoder.encode("test_passw")).roles("USER")
      			.and()
      			.withUser("admin_usr").password(encoder.encode("admin_passw")).roles("USER", "ADMIN");
    }
}
