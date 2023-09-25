package br.com.eljc.Features.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@TestMethodOrder(MethodOrderer.Random.class)
public class OrderAnnotationTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	@Order(1)
	void autowireWorked() {
		assertNotNull(mvc);
	}
	
	@Test
	@Order(3)
	void testHelloWithoutName() throws Exception{
		mvc.perform(get("/hello").accept(MediaType.TEXT_HTML))
		.andExpect(status().isOk())
		.andExpect(view().name("hello"))
		.andExpect(model().attribute("user", "World"));
	}
	
	@Test
	@Order(2)
	void testHelloWithName() throws Exception{
		mvc.perform(get("/hello").param("name", "Camara").accept(MediaType.TEXT_HTML))
		.andExpect(status().isOk())
		.andExpect(view().name("hello"))
		.andExpect(model().attribute("user", "Camara"));
	}
	
	@AfterAll
	public static void finish() {
		System.out.println("Finished Ordered Tests");
	}
}
