package br.com.eljc.Features.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import br.com.eljc.Features.model.GreentingSingleton;
import br.com.eljc.Features.model.GreetingPrototype;

@SpringBootTest
public class GreetingSingletonTest {

	@Autowired
	private ApplicationContext context;
	
	@Test
	void getBeanTwice() {
		GreentingSingleton greeting1 = context.getBean(GreentingSingleton.class);
		GreentingSingleton greeting2 = context.getBean(GreentingSingleton.class);
		greeting2.setMessage("First message");
		greeting1.setMessage("What up?");
		
		System.out.println("Greeting 1: "+greeting1.getMessage());
		
		
		System.out.println("Greeting 2: "+greeting2.getMessage());
		//BY DEFAULT:  ALL OBJECTS IN THE APPLICATION CONTEXT ARE SINGLETONS - SPRING PRINCIPLE
		
	}
	
	@Test
	void testScopePrototype() {
		GreetingPrototype greetingPrototype1 = context.getBean(GreetingPrototype.class);
		GreetingPrototype greetingPrototype2 = context.getBean(GreetingPrototype.class);
		
		greetingPrototype2.setMessage("First message");
		greetingPrototype1.setMessage("What up?");
		
		System.out.println("greetingPrototype 1: "+greetingPrototype1.getMessage());
		
		
		System.out.println("greetingPrototype 2: "+greetingPrototype2.getMessage());
	}
}
