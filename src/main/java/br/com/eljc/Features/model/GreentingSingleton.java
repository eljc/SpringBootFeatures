package br.com.eljc.Features.model;

import org.springframework.stereotype.Component;

@Component
public class GreentingSingleton {
	
	private String message;
	
	public GreentingSingleton() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
