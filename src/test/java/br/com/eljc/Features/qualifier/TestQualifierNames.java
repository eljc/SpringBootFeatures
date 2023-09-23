package br.com.eljc.Features.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestQualifierNames {

	@Autowired
	@Qualifier("quick")
	private Algorithm algo;
	
	@Test
	void testAlgo() {
		int[] numbers = {3,4,2,1};
		algo.sort(numbers);
	}
}
