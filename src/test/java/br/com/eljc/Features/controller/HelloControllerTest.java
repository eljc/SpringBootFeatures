package br.com.eljc.Features.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

class HelloControllerTest {

	@Test
	void testSayHello() {
		HelloController controller = new HelloController();
		Model model = new BindingAwareModelMap();
		String result = controller.sayHello("world", model);
		assertAll(
				() -> assertEquals("world", model.getAttribute("user")),
				() -> assertEquals("hello", result)
				);
	}

}
