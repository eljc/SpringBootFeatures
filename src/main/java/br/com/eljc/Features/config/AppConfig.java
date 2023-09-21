package br.com.eljc.Features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eljc.Features.model.Greeting;

@Configuration
public class AppConfig {
	
	@Bean
	public Greeting defaultGreeting() {
		return new Greeting("Hello, World!");
	}
	
	/*
	 * NoUniqueBeanDefinitionException
	 * 
	@Bean
	public Greeting outroNomeMethod() {
		return new Greeting("Hello, World!");
	}
	*/
	
	@Bean("otherBean")
	public Greeting otherBean() {
		return new Greeting("Hello, other bean World!");
	}

}
