package com.javalopment.workshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class FormLoginSecrityConfig extends WebSecurityConfigurerAdapter {

	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http
 			.requestMatchers()
 				.antMatchers("/form/**", "/login", "/logout")
 				.and().authorizeRequests()
 				.anyRequest().authenticated()
 				.and().formLogin()
 					//.loginPage("/form/")
					//.failureHandler(failureHandler())
					//.loginProcessingUrl("/form/login")
					//.usernameParameter("username")
					//.passwordParameter("password")
					//.successHandler(successHandler())
 				.and().logout()
						.deleteCookies("remove") // --> CookieClearingLogoutHandler 
						.invalidateHttpSession(true) // --> SecurityContextLogoutHandler
						//.logoutUrl("/custom-logout") // --> POST
						.logoutRequestMatcher(new AntPathRequestMatcher("/custom-logout")) // --> GET
						.logoutSuccessUrl("/form/loggedout")
				//.and().rememberMe().key("key here")
				.and().exceptionHandling()
						.accessDeniedPage("/form/loginfail");
 		 		 		
 	}
	
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    
//    	auth
//    		.inMemoryAuthentication()
//      			.withUser("test_usr").password(encoder.encode("test_passw")).roles("USER")
//      			.and()
//      			.withUser("admin_usr").password(encoder.encode("admin_passw")).roles("USER", "ADMIN");
//    }
}
