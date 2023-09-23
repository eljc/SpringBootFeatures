package br.com.eljc.Features.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
@Qualifier("sigle")
public class PersonSingleton {
	
	private String message;
	
	public String getMessage() {
	    return message;	   	   
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
