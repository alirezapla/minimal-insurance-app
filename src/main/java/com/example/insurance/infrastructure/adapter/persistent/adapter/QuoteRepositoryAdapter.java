package com.example.insurance.infrastructure.adapter.persistent.adapter;

import com.example.insurance.application.exceptions.ProviderNotFoundException;
import com.example.insurance.application.exceptions.QuoteNotFoundException;
import com.example.insurance.domain.port.secondary.QuoteRepositoryPort;
import com.example.insurance.infrastructure.adapter.persistent.entity.ProviderEntity;
import com.example.insurance.infrastructure.adapter.persistent.entity.QuoteEntity;
import com.example.insurance.infrastructure.adapter.persistent.mapper.QuoteMapper;
import com.example.insurance.infrastructure.adapter.persistent.repository.JpaProviderRepository;
import com.example.insurance.infrastructure.adapter.persistent.repository.JpaQuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.example.insurance.domain.model.Quote;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class QuoteRepositoryAdapter implements QuoteRepositoryPort {

    private final JpaQuoteRepository jpaQuoteRepository;
    private final JpaProviderRepository jpaProviderRepository;

    public QuoteRepositoryAdapter(JpaQuoteRepository jpaQuoteRepository, JpaProviderRepository jpaProviderRepository) {
        this.jpaQuoteRepository = jpaQuoteRepository;
        this.jpaProviderRepository = jpaProviderRepository;
    }

    @Override
    public Optional<Quote> findById(UUID id) {
        return jpaQuoteRepository.findByUuid(id).map(QuoteMapper::toDomain);
    }

    @Override
    public Optional<Quote> findLowestPrice() {
        return jpaQuoteRepository.findLowestPriceQuote(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(QuoteMapper::toDomain);
    }


    @Override
    public Optional<Quote> findHighestPrice() {
        return jpaQuoteRepository.findHighestPriceQuote(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(QuoteMapper::toDomain);
    }

    @Override
    public Page<Quote> findAll(Pageable pageable) {
        return jpaQuoteRepository.findAll(pageable)
                .map(QuoteMapper::toDomain);
    }

    @Override
    public List<Quote> findAll() {
        return jpaQuoteRepository.findAll()
                .stream()
                .map(QuoteMapper::toDomain)
                .toList();
    }

    @Override
    public Quote save(Quote quote) {
        ProviderEntity provider = jpaProviderRepository.findByName(quote.getProviderName())
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found"));
        QuoteEntity saved = jpaQuoteRepository.save(QuoteMapper.toEntity(quote, provider));
        return QuoteMapper.toDomain(saved);
    }

    @Override
    public Quote update(Quote quote) {
        QuoteEntity existing = jpaQuoteRepository.findByUuid(quote.getId())
                .orElseThrow(() -> new QuoteNotFoundException("Quote not found"));
        if (!existing.getProvider().getName().equals(quote.getProviderName())) {
            ProviderEntity provider = jpaProviderRepository.findByName(quote.getProviderName())
                    .orElseThrow(() -> new ProviderNotFoundException("Provider not found"));
            existing.setProvider(provider);
        }
        existing.setPrice(quote.getPrice());
        existing.setCoverageType(quote.getCoverageType());
        QuoteEntity updated = jpaQuoteRepository.save(existing);
        return QuoteMapper.toDomain(updated);
    }

    @Override
    public void deleteById(UUID id) {
        jpaQuoteRepository.deleteByUuid(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaQuoteRepository.existsByUuid(id);
    }

    @Override
    public List<Quote> findAllByOrderByPriceAsc() {
        return jpaQuoteRepository.findAllByOrderByPriceAsc()
                .stream()
                .map(QuoteMapper::toDomain)
                .toList();
    }
}
