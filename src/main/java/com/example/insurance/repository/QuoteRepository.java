package com.example.insurance.repository;

import com.example.insurance.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {
    List<Quote> findAllByOrderByPriceAsc();
}