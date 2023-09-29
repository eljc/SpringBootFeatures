package br.com.eljc.Features.controller;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.eljc.Features.model.CashCard;
import br.com.eljc.Features.repository.CashCardRepository;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
	
	 private CashCardRepository cashCardRepository;

	    public CashCardController(CashCardRepository cashCardRepository) {
	        this.cashCardRepository = cashCardRepository;
	    }

	    @GetMapping("/{requestedId}")
	    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId, 
	    		Principal principal) {
	        Optional<CashCard> cashCardOptional = Optional.ofNullable(cashCardRepository.findByIdAndOwner(requestedId, principal.getName()));
	        if (cashCardOptional.isPresent()) {
	            return ResponseEntity.ok(cashCardOptional.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping
	    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb,
	    		Principal principal) {
	    	CashCard cashCardWithOwner = new CashCard(null, newCashCardRequest.amount(), principal.getName());
	        CashCard savedCashCard = cashCardRepository.save(cashCardWithOwner);
	        URI locationOfNewCashCard = ucb
	                 .path("cashcards/{id}")
	                 .buildAndExpand(savedCashCard.id())
	                 .toUri();
	        return ResponseEntity.created(locationOfNewCashCard).build();
	     }

	    @GetMapping()
	    public ResponseEntity<Iterable<CashCard>> findAll(Pageable pageble, Principal principal) {
	       Page<CashCard> page = cashCardRepository.findByOwner(principal.getName(),
	    		   PageRequest.of(pageble.getPageNumber(), 
	    				   pageble.getPageSize(),
	    				   pageble.getSortOr(Sort.by(Sort.Direction.ASC,"amount")))
	    		   );	 
	    	
	    	return ResponseEntity.ok(page.getContent());
	    }
}