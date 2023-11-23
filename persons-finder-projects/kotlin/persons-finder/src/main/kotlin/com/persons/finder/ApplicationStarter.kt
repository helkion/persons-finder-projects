package com.persons.finder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import com.persons.finder.vo.LoginDetails

@RestController
@SpringBootApplication
class ApplicationStarter

fun main(args: Array<String>) {
	runApplication<ApplicationStarter>(*args)
}

@GetMapping("/api/v1")
fun index(): String {
	return "Persons' finder API v1"
}

@GetMapping("/api/v1/login")
fun login(): LoginDetails {
	val login = LoginDetails()
	login.result = true
	return login
}