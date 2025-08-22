package com.example.insurance.infrastructure.adapter.persistent.repository;

import java.util.UUID;

public interface QuoteProjection {
    UUID getUuid();
    Double getPrice();
    String getProviderName();
}