package com.persons.finder.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class Config {
	
	@Autowired
    private BasicAuthenticationEntryPoint authenticationEntryPoint;
    
    @Bean
    public SecurityFilterChain filterChain(
    		HttpSecurity http) 
    				throws Exception {
        http
	        .authorizeRequests()
	            .antMatchers("/api/v1", "/error")
	            .permitAll()
	            .anyRequest().authenticated().and()
	        .httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
	        .csrf().disable();
        
        return http.build();
    }
    
}
