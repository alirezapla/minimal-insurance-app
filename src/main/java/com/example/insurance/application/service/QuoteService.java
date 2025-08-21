package com.example.insurance.application.service;

import com.example.insurance.application.service.abstraction.QuoteServiceUseCase;
import com.example.insurance.domain.exceptions.QuoteNotFoundException;
import com.example.insurance.domain.model.Quote;
import com.example.insurance.domain.port.secondary.ProviderRepositoryPort;
import com.example.insurance.domain.port.secondary.QuoteRepositoryPort;
import com.example.insurance.infrastructure.adapter.web.dto.QuoteRequestDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuoteService implements QuoteServiceUseCase {

    private final QuoteRepositoryPort quoteRepository;

    public QuoteService(QuoteRepositoryPort quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Cacheable(value = "quote", key = "#id")
    public Quote getQuoteById(UUID id) throws QuoteNotFoundException {
        return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("Quote not found"));
    }

    @CacheEvict(value = {"quote", "quotes"}, allEntries = true)
    public Quote createQuote(String coverageType, Double price, String providerName) {
        Quote quote = new Quote();
        quote.setCoverageType(coverageType);
        quote.setPrice(price);
        quote.setProviderName(providerName);
        return quoteRepository.save(quote);
    }

    @Cacheable(value = "quotes")
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @CachePut(value = "quote", key = "#id")
    public Quote updateQuote(UUID id, QuoteRequestDTO updatedQuote) throws QuoteNotFoundException {
        Quote existingQuote = new Quote(id);
        existingQuote.setCoverageType(updatedQuote.coverageType());
        existingQuote.setPrice(updatedQuote.price());
        existingQuote.setProviderName(updatedQuote.providerName());
        return quoteRepository.update(existingQuote);
    }

    @CacheEvict(value = {"quote", "quotes"}, key = "#id", allEntries = true)
    public void deleteQuote(UUID id) {
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException("Quote with id " + id + " not found");
        }
        quoteRepository.deleteById(id);
    }

    @Cacheable("quotes")
    public void applyDiscountToQuote(UUID id, double discount) throws QuoteNotFoundException {
        Quote quote = getQuoteById(id);
        DiscountVisitor visitor = new DiscountVisitor(discount);
        quote.accept(visitor);
        quoteRepository.save(quote);
    }

    @Cacheable("quotes")
    public Quote getLowestPriceQuote() {
        LowestPriceVisitor visitor = new LowestPriceVisitor();
        quoteRepository.findAll().forEach(q -> q.accept(visitor));
        return visitor.getBestQuote();
    }

    @Cacheable("quotes")
    public Quote getHighestPriceQuote() {
        HighestPriceVisitor visitor = new HighestPriceVisitor();
        quoteRepository.findAll().forEach(q -> q.accept(visitor));
        return visitor.getBestQuote();
    }

    @Cacheable("quotes")
    public List<Quote> getSortedQuotes(boolean ascending) {
        SortedQuotesVisitor visitor = new SortedQuotesVisitor();
        quoteRepository.findAll().forEach(q -> q.accept(visitor));
        return ascending ? visitor.getSortedQuotesAsc() : visitor.getSortedQuotesDesc();
    }

}