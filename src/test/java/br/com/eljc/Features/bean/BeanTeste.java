package br.com.eljc.Features.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import br.com.eljc.Features.model.Greeting;

@SpringBootTest
public class BeanTeste {

	@Autowired
	private ApplicationContext context;
	
	@Test
	void getBean() {
		Greeting greeting1 = context.getBean("defaultGreeting", Greeting.class);
		System.out.println(greeting1);
		
		Greeting greeting2 = context.getBean("otherBean", Greeting.class);
		System.out.println(greeting2);
	}
}
