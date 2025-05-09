package com.example.insurance.controller;


import com.example.insurance.common.exceptions.QuoteNotFoundException;
import com.example.insurance.controller.utils.QuoteRequest;
import com.example.insurance.model.Quote;
import com.example.insurance.service.QuoteService;
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

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<Quote> create(@RequestBody QuoteRequest request, UriComponentsBuilder uriBuilder) throws URISyntaxException {
        var createdQuote = quoteService.createQuote(request.getCoverageType(), request.getPrice(), request.getProviderName());
        URI location = uriBuilder
                .path("/quotes/{id}")
                .buildAndExpand(createdQuote.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdQuote);
    }

    @GetMapping("/{id}")
    public Quote get(@PathVariable UUID id) {
        return quoteService.getQuoteById(id);
    }

    @GetMapping
    public List<Quote> all() {
        return quoteService.getAllQuotes();
    }

    @PutMapping("/{id}")
    public Quote update(@PathVariable UUID id, @RequestBody QuoteRequest request) throws QuoteNotFoundException {
        return quoteService.updateQuote(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        quoteService.deleteQuote(id);
    }

    @GetMapping("/aggregate")
    public ResponseEntity<?> aggregate(
            @RequestParam(defaultValue = "lowest") String type,
            @RequestParam(defaultValue = "true") boolean ascending) {

        switch (type.toLowerCase()) {
            case "lowest":
                return ResponseEntity.ok(quoteService.getLowestPriceQuote());
            case "highest":
                return ResponseEntity.ok(quoteService.getHighestPriceQuote());
            case "sort":
                return ResponseEntity.ok(quoteService.getSortedQuotes(ascending));
            default:
                return ResponseEntity.badRequest().body("Unknown aggregate type: " + type);
        }
    }
}