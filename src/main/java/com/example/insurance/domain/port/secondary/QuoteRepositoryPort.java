package com.example.insurance.domain.port.secondary;



import com.example.insurance.domain.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuoteRepositoryPort {
    Optional<Quote> findById(UUID id);
    Optional<Quote> findLowestPrice();
    Optional<Quote> findHighestPrice();

    Page<Quote> findAll(Pageable pageable);
    List<Quote> findAll();
    Quote save(Quote quote);
    Quote update(Quote quote);
    void deleteById(UUID id);
    boolean existsById(UUID id);
    List<Quote> findAllByOrderByPriceAsc();
}
