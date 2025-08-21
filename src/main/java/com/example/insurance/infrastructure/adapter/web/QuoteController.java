package com.example.insurance.infrastructure.adapter.web;


import com.example.insurance.domain.exceptions.QuoteNotFoundException;
import com.example.insurance.domain.model.Quote;
import com.example.insurance.application.service.abstraction.QuoteServiceUseCase;
import com.example.insurance.infrastructure.adapter.web.dto.QuoteRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteServiceUseCase quoteService;

    public QuoteController(QuoteServiceUseCase quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<Quote> create(@RequestBody QuoteRequestDTO request, UriComponentsBuilder uriBuilder) throws URISyntaxException {

        var createdQuote = quoteService.createQuote(request.coverageType(), request.price(), request.providerName());
        URI location = uriBuilder
                .path("/quotes/{id}")
                .buildAndExpand(createdQuote.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdQuote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> get(@PathVariable UUID id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Quote>> all() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> update(@PathVariable UUID id, @RequestBody QuoteRequestDTO request) throws QuoteNotFoundException {
        return ResponseEntity.ok(quoteService.updateQuote(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable UUID id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aggregate")
    public ResponseEntity<?> aggregate(
            @RequestParam(defaultValue = "lowest") String type,
            @RequestParam(defaultValue = "true") boolean ascending) {

        return switch (type.toLowerCase()) {
            case "lowest" -> ResponseEntity.ok(quoteService.getLowestPriceQuote());
            case "highest" -> ResponseEntity.ok(quoteService.getHighestPriceQuote());
            case "sort" -> ResponseEntity.ok(quoteService.getSortedQuotes(ascending));
            default -> ResponseEntity.badRequest().body("Unknown aggregate type: " + type);
        };
    }
}