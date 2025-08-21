package com.example.insurance.application.service.abstraction;

import com.example.insurance.domain.exceptions.QuoteNotFoundException;
import com.example.insurance.domain.model.Quote;
import com.example.insurance.infrastructure.adapter.web.dto.QuoteRequestDTO;

import java.util.List;
import java.util.UUID;

public interface QuoteServiceUseCase {
    Quote getQuoteById(UUID id) throws QuoteNotFoundException;

    Quote createQuote(String coverageType, Double price, String providerName);

    List<Quote> getAllQuotes();

    Quote updateQuote(UUID id, QuoteRequestDTO updatedQuote) throws QuoteNotFoundException;

    void deleteQuote(UUID id);

    void applyDiscountToQuote(UUID id, double discount) throws QuoteNotFoundException;

    Quote getLowestPriceQuote();

    Quote getHighestPriceQuote();

    List<Quote> getSortedQuotes(boolean ascending);

}
