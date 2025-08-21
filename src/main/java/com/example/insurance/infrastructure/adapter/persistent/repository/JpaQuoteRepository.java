package com.example.insurance.infrastructure.adapter.persistent.repository;

import com.example.insurance.infrastructure.adapter.persistent.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaQuoteRepository extends JpaRepository<QuoteEntity, Long> {
    List<QuoteEntity> findAllByOrderByPriceAsc();
    Optional<QuoteEntity> findByUuid(UUID uuid);
    boolean existsByUuid(UUID id);
    void deleteByUuid(UUID id);
}