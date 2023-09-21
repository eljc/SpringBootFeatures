package br.com.eljc.Features.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import br.com.eljc.Features.model.Person;

public class PersonSingleton {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Person personSingleton() {
	    return new Person();
	}
}
