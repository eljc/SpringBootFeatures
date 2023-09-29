package br.com.eljc.Features;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import br.com.eljc.Features.model.GreentingSingleton;
import br.com.eljc.Features.model.Greeting;

@SpringBootTest
class FeaturesApplicationTests {
	
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context);
		System.out.println(context.getClass().getName());
		System.out.println(context.getBeanDefinitionCount());
		
		Arrays.stream(
		context.getBeanDefinitionNames()).forEach(System.out::println);
		// the name of the class is the name of the bean
		/*
		String[] beans = context.getBeanDefinitionNames();
		for (String strBean : beans) {
			System.out.println(strBean);
		} 
		*/
		
	}
	/*
	@Test @Disabled
	void noBeanException() {
		assertThrows(NoSuchBeanDefinitionException.class, 
				() -> context.getBean(Greeting.class));
	}
	*/


}
