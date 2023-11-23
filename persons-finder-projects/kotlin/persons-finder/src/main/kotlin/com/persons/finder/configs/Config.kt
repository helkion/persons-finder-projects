package com.persons.finder.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint

@Configuration
class Config {

    @Autowired
    private lateinit var authenticationEntryPoint: BasicAuthenticationEntryPoint

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeRequests()
            .antMatchers("/api/v1", "/error")
            .permitAll()
            .anyRequest().authenticated().and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
            .csrf().disable()
            .build()
    }
}