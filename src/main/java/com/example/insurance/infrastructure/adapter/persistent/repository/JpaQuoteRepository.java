package com.example.insurance.infrastructure.adapter.persistent.repository;

import com.example.insurance.infrastructure.adapter.persistent.entity.QuoteEntity;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT q.id as uuid, q.price as price, q.provider.name as providerName " +
            "FROM QuoteEntity q ORDER BY q.price ASC")
    List<QuoteProjection> findLowestPriceQuote(Pageable pageable);

    @Query("SELECT q.id as uuid, q.price as price, q.provider.name as providerName " +
            "FROM QuoteEntity q ORDER BY q.price DESC")
    List<QuoteProjection> findHighestPriceQuote(Pageable pageable);
}