package com.rchomczyk.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class HttpSecurityConfiguration {

	@Bean
	public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity)
		throws Exception {
		httpSecurity.authorizeRequests()
			.antMatchers("/public/*")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.logout()
			.permitAll()
			.and()
			.oauth2Login()
			.permitAll()
			.defaultSuccessUrl("/");
		return httpSecurity.build();
	}
}
