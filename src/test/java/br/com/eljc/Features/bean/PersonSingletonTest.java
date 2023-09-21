package br.com.eljc.Features.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import br.com.eljc.Features.model.Person;

class PersonSingletonTest {
	
	private static final String NAME = "John Smith";
	private static final String NAME2 = "Camara";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private PersonSingleton personSingleton = new PersonSingleton();
	
	@Autowired
	private ApplicationContext context;

	@Test
	void givenSingletonScope_whenSetName_thenEqualNames() {
		/*
		context.getBean(PersonSingleton.class);
		
		String[] beans = context.getBeanDefinitionNames();
		
		
		
		for (String stBean : beans) {
			System.out.println(stBean);
		} 
	    */
	    
		personSingleton.personSingleton().setName(NAME);

	    Person personSingletonA = personSingleton.personSingleton();
	    Person personSingletonB = personSingleton.personSingleton();;

	    personSingletonA.setName(NAME2);
	   // personSingletonB.setName(NAME2);
	    assertEquals(NAME2, personSingleton.personSingleton().getName());
	    
	}

}
