package com.example.insurance.application.port.abstraction;

import com.example.insurance.application.exceptions.QuoteNotFoundException;
import com.example.insurance.domain.model.Quote;
import com.example.insurance.infrastructure.adapter.web.dto.QuoteRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface QuoteServiceUseCase {
    Quote getQuoteById(UUID id) throws QuoteNotFoundException;

    Quote createQuote(String coverageType, Double price, String providerName);

    Page<Quote> getAllQuotes(Pageable pageable);

    Quote updateQuote(UUID id, QuoteRequestDTO updatedQuote) throws QuoteNotFoundException;

    void deleteQuote(UUID id);

    void applyDiscountToQuote(UUID id, double discount) throws QuoteNotFoundException;

    Quote getLowestPriceQuote();

    Quote getHighestPriceQuote();

    List<Quote> getSortedQuotes(boolean ascending);

}
