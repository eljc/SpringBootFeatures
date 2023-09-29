package br.com.eljc.Features.model;

import org.springframework.data.annotation.Id;

public record CashCard(@Id Long id, Double amount, String owner) {

}
