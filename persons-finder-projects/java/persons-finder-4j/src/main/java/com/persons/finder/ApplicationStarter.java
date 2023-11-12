package com.persons.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persons.finder.vo.LoginDetails;

@RestController
@SpringBootApplication
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}
	
	@GetMapping("/api/v1")
    public String index() {
		return "Persons' finder API v1";
	}

//	@CrossOrigin(origins = { "http://localhost:3000" })
	@GetMapping("/api/v1/login")
    public LoginDetails login() {
		LoginDetails login = new LoginDetails();
		login.setResult(true);
		return login;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
