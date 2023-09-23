package br.com.eljc.Features.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class PersonSingletonTest {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private static final String NAME = "John Smith";
	private static final String NAME2 = "Camara";
	
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
	    
		

	    PersonSingleton personSingleton = context.getBean(PersonSingleton.class);
	    
	    personSingleton.setMessage(NAME);
		
	    String msgA= personSingleton.getMessage();
	    String msgB = personSingleton.getMessage();
	    
	    LOGGER.info("Message A: {}", msgA);
	    personSingleton.setMessage(NAME2);	
	    LOGGER.info("Message B: {}", msgB);
	    
	    assertEquals(msgA, msgB);
	    
	}

}
