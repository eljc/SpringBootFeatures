package br.com.eljc.Features.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
public class HelloControllerMockMVCTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	void autowireWorked() {
		assertNotNull(mvc);
	}
	
	@Test
	void testHelloWithoutName() throws Exception{
		mvc.perform(get("/hello").accept(MediaType.TEXT_HTML))
		.andExpect(status().isOk())
		.andExpect(view().name("hello"))
		.andExpect(model().attribute("user", "World"));
	}
	
	@Test
	void testHelloWithName() throws Exception{
		mvc.perform(get("/hello").param("name", "Camara").accept(MediaType.TEXT_HTML))
		.andExpect(status().isOk())
		.andExpect(view().name("hello"))
		.andExpect(model().attribute("user", "Camara"));
	}
}
