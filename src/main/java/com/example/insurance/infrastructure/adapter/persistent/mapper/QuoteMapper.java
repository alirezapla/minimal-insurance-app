package com.example.insurance.infrastructure.adapter.persistent.mapper;

import com.example.insurance.domain.model.Quote;
import com.example.insurance.infrastructure.adapter.persistent.entity.*;
import com.example.insurance.infrastructure.adapter.persistent.repository.QuoteProjection;

public class QuoteMapper {

    public static Quote toDomain(QuoteEntity entity) {
        if (entity == null) return null;
        Quote quote = new Quote();
        quote.setId(entity.getUuid());
        quote.setCoverageType(entity.getCoverageType());
        quote.setPrice(entity.getPrice());
        quote.setProviderName(entity.getProvider().getName());
        return quote;
    }
    public static Quote toDomain(QuoteProjection entity) {
        if (entity == null) return null;
        Quote quote = new Quote();
        quote.setId(entity.getUuid());
        quote.setPrice(entity.getPrice());
        quote.setProviderName(entity.getProviderName());
        return quote;
    }

    public static QuoteEntity toEntity(Quote domain, ProviderEntity provider) {
        if (domain == null) return null;
        QuoteEntity entity = new QuoteEntity();
        entity.setCoverageType(domain.getCoverageType());
        entity.setPrice(domain.getPrice());
        entity.setProvider(provider);
        return entity;
    }
}