package br.com.eljc.Features.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.eljc.Features.model.Greeting;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloRestControllerFunctionalTest {
	
	//@Autowired
	//private TestRestTemplate template;
	
	@Test
	void greetWithName(@Autowired TestRestTemplate template) {
		Greeting response = template.getForObject("/rest?name=Camara", Greeting.class);
		assertEquals("Hello, Camara!", response.getMessage());
	}

	@Test
	void greetWithoutName(@Autowired TestRestTemplate template) {
		ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
/*		assertAll(
				() -> assertEquals(HttpStatus.OK, entity.getStatusCode()),
				() -> assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType())
				);
*/		
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
		Greeting gree = entity.getBody();
		assertEquals("Hello, World!", gree.getMessage());
	}
}
