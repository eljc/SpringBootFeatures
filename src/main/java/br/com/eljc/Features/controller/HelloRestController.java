package br.com.eljc.Features.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eljc.Features.model.Greeting;

@RestController
public class HelloRestController {
	
	@GetMapping("/rest")
	public Greeting greet(@RequestParam(required = false, 
			defaultValue = "World") 
			String name) {
		return new Greeting("Hello, "+name+"!");
	}

}
