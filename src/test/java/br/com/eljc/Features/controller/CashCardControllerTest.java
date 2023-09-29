package br.com.eljc.Features.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import br.com.eljc.Features.model.CashCard;
import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*
 * The reason here is to clean up after creating a new Cach Card
 */
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class CashCardControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnACashCardWhenDataIsSaved() {
		 ResponseEntity<String> response = restTemplate
		            .withBasicAuth("sarah1", "abc123") // Add this
		            .getForEntity("/cashcards/99", String.class);
		        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		/*
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);

		Double amount = documentContext.read("$.amount");
		assertThat(amount).isEqualTo(123.45);
		*/
	}

	@Test
	void shouldNotReturnACashCardWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123")
				.getForEntity("/cashcards/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	@DirtiesContext
	void shouldCreateANewCashCard() {
		CashCard newCashCard = new CashCard(null, 250.00, null);
		ResponseEntity<Void> createResponse = restTemplate.withBasicAuth("sarah1", "abc123")
				.postForEntity("/cashcards", newCashCard, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
		System.out.println("location of new cash card: "+locationOfNewCashCard.getPath());
		ResponseEntity<String> getResponse = restTemplate.withBasicAuth("sarah1", "abc123")
				.getForEntity(locationOfNewCashCard, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// Add assertions such as these
		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		Double amount = documentContext.read("$.amount");

		assertThat(id).isNotNull();
		assertThat(amount).isEqualTo(250.00);
	}

	 @Test
	 void shouldReturnAllCashCardsWhenListIsRequested() {
	     ResponseEntity<String> response = restTemplate
	    		 .withBasicAuth("sarah1", "abc123")
	    		 .getForEntity("/cashcards", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     int cashCardCount = documentContext.read("$.length()");
	     assertThat(cashCardCount).isEqualTo(3);

	     JSONArray ids = documentContext.read("$..id");
	     assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);

	     JSONArray amounts = documentContext.read("$..amount");
	     assertThat(amounts).containsExactlyInAnyOrder(1.0, 123.45, 150.00);
	 }
	 
	 @Test
	 void shouldReturnAPageOfCashCards() {
	     ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123")
	    		 .getForEntity("/cashcards?page=0&size=1", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     JSONArray page = documentContext.read("$[*]");
	     assertThat(page.size()).isEqualTo(1);
	 }
	 
	 @Test
	 void shouldReturnASortedPageOfCashCards() {
		 
	     ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123") // Add this
	    		 .getForEntity("/cashcards?page=0&size=1&sort=amount,desc", String.class);
	     
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     JSONArray read = documentContext.read("$[*]");
	     assertThat(read.size()).isEqualTo(1);

	     double amount = documentContext.read("$[0].amount");
	     assertThat(amount).isEqualTo(150.00);
	     
	     
	     ResponseEntity<String> response2 = restTemplate.withBasicAuth("sarah1", "abc123")
	    		 .getForEntity("/cashcards?page=0&size=1&sort=amount,asc", String.class);
	     
	     DocumentContext documentContext2 = JsonPath.parse(response2.getBody());
	     JSONArray read2 = documentContext2.read("$[*]");
	     assertThat(read2.size()).isEqualTo(1);

	     double amount2 = documentContext2.read("$[0].amount");
	     assertThat(amount2).isEqualTo(1.00);

	     
	 }
	 
	 @Test
	 void shouldReturnASortedPageOfCashCardsWithNoParametersAndUseDefaultValues() {
	     ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123")
	    		 .getForEntity("/cashcards", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     JSONArray page = documentContext.read("$[*]");
	     assertThat(page.size()).isEqualTo(3);

	     JSONArray amounts = documentContext.read("$..amount");
	     assertThat(amounts).containsExactly(1.0, 123.45, 150.00);
	 }
	 
	 @Test
	 void shouldNotReturnACashCardWhenUsingBadCredentials() {
		 ResponseEntity<String> response = restTemplate
			      .withBasicAuth("BAD-USER", "abc123")
			      .getForEntity("/cashcards/99", String.class);
			    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

			    response = restTemplate
			      .withBasicAuth("sarah1", "BAD-PASSWORD")
			      .getForEntity("/cashcards/99", String.class);
			    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	 }
	 
	 @Test
	 void shouldRejectUsersWhoAreNotCardOwners() {
	     ResponseEntity<String> response = restTemplate
	       .withBasicAuth("hank-owns-no-cards", "qrs456")
	       .getForEntity("/cashcards/99", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	 }
	 
	 @Test
	 void shouldNotAllowAccessToCashCardsTheyDoNotOwn() {
	     ResponseEntity<String> response = restTemplate
	       .withBasicAuth("sarah1", "abc123")
	       .getForEntity("/cashcards/102", String.class); // kumar2's data
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	 }
}