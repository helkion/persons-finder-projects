//package com.persons.finder.configs
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//
//@EnableWebSecurity
//class SecurityConfiguration {
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//
//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val user: UserDetails = User.builder()
//            .username("sa")
//            .password(passwordEncoder().encode("password"))
//            .roles("USER")
//            .build()
//
//        return InMemoryUserDetailsManager(user)
//    }
//
//    @Throws(Exception::class)
//    fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder())
//    }
//
//    @Throws(Exception::class)
//    fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic()
//    }
//}
