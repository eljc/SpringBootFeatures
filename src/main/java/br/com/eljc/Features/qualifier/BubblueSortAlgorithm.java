package br.com.eljc.Features.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubblueSortAlgorithm implements Algorithm {

	@Override
	public int[] sort(int[] numbers) {
		return numbers;
	}

}
