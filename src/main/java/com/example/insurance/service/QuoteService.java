package com.example.insurance.service;

import com.example.insurance.common.exceptions.ProviderNotFoundException;
import com.example.insurance.common.exceptions.QuoteNotFoundException;
import com.example.insurance.controller.utils.QuoteRequest;
import com.example.insurance.model.Provider;
import com.example.insurance.model.Quote;
import com.example.insurance.repository.ProviderRepository;
import com.example.insurance.repository.QuoteRepository;
import com.example.insurance.service.abstraction.IQuoteService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuoteService implements IQuoteService {

    private final QuoteRepository quoteRepository;
    private final ProviderRepository providerRepository;

    public QuoteService(QuoteRepository quoteRepository, ProviderRepository providerRepository) {
        this.quoteRepository = quoteRepository;
        this.providerRepository = providerRepository;
    }

    @Cacheable(value = "quotes", key = "#id")
    public Quote getQuoteById(UUID id) throws QuoteNotFoundException {
        return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("Quote not found"));
    }

    @CacheEvict(value = {"quotes", "bestQuote"}, allEntries = true)
    public Quote createQuote(String coverageType, Double price, String providerName) {
        Quote quote = new Quote();
        quote.setCoverageType(coverageType);
        quote.setPrice(price);
        quote.setProvider(getProvider(providerName));
        return quoteRepository.save(quote);
    }

    @Cacheable(value = "quotes")
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @CachePut(value = "quotes", key = "#id")
    public Quote updateQuote(UUID id, QuoteRequest updatedQuote) throws QuoteNotFoundException {
        Quote existingQuote = getQuoteById(id);
        existingQuote.setCoverageType(updatedQuote.getCoverageType());
        existingQuote.setPrice(updatedQuote.getPrice());
        existingQuote.setProvider(getProvider(updatedQuote.getProviderName()));
        return quoteRepository.save(existingQuote);
    }

    @CacheEvict(value = "quotes", key = "#id")
    public void deleteQuote(UUID id) {
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException("Quote with id " + id + " not found");
        }
        quoteRepository.deleteById(id);
    }

    @Cacheable("aggregateQuotes")
    public void applyDiscountToQuote(UUID id, double discount) throws QuoteNotFoundException {
        Quote quote = getQuoteById(id);
        DiscountVisitor visitor = new DiscountVisitor(discount);
        quote.accept(visitor);
        quoteRepository.save(quote);
    }

    @Cacheable("aggregateQuotes")
    public Quote getLowestPriceQuote() {
        LowestPriceVisitor visitor = new LowestPriceVisitor();
        quoteRepository.findAll().forEach(q -> q.accept(visitor));
        return visitor.getBestQuote();
    }

    @Cacheable("aggregateQuotes")
    public Quote getHighestPriceQuote() {
        HighestPriceVisitor visitor = new HighestPriceVisitor();
        quoteRepository.findAll().forEach(q -> q.accept(visitor));
        return visitor.getBestQuote();
    }

    @Cacheable("aggregateQuotes")
    public List<Quote> getSortedQuotes(boolean ascending) {
        SortedQuotesVisitor visitor = new SortedQuotesVisitor();
        quoteRepository.findAll().forEach(q -> q.accept(visitor));
        return ascending ? visitor.getSortedQuotesAsc() : visitor.getSortedQuotesDesc();
    }

    private Provider getProvider(String name) {
        return providerRepository.findByName(name)
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found"));
    }
}