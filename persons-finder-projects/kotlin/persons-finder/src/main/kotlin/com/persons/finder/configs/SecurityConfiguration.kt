package com.persons.finder.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val encoder = BCryptPasswordEncoder(16)
        auth.inMemoryAuthentication()
            .withUser("sa")
            .password(encoder.encode("password"))
            .roles("USER")
    }

    @Bean
    fun basicAuthenticationEntryPoint(): BasicAuthenticationEntryPoint {
        val entryPoint = BasicAuthenticationEntryPoint()
        entryPoint.realmName = "myRealm"
        return entryPoint
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowCredentials = true
        configuration.addAllowedOrigin("http://localhost:3000")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        val bean = FilterRegistrationBean(CorsFilter(corsConfigurationSource()))
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}