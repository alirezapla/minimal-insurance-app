package com.example.insurance.service.abstraction;

import com.example.insurance.common.exceptions.QuoteNotFoundException;
import com.example.insurance.controller.utils.QuoteRequest;
import com.example.insurance.model.Quote;

import java.util.List;
import java.util.UUID;

public interface IQuoteService {
    Quote getQuoteById(UUID id) throws QuoteNotFoundException;

    Quote createQuote(String coverageType, Double price, String providerName);

    List<Quote> getAllQuotes();

    Quote updateQuote(UUID id, QuoteRequest updatedQuote) throws QuoteNotFoundException;

    void deleteQuote(UUID id);

    void applyDiscountToQuote(UUID id, double discount) throws QuoteNotFoundException;

    Quote getLowestPriceQuote();

    Quote getHighestPriceQuote();

    List<Quote> getSortedQuotes(boolean ascending);


}
