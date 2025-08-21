package com.example.insurance.domain.port.secondary;

import com.example.insurance.domain.model.Provider;
import com.example.insurance.infrastructure.adapter.persistent.entity.ProviderEntity;

import java.util.Optional;
import java.util.UUID;

public interface ProviderRepositoryPort {
    Optional<Provider> findByName(String name);
    Optional<Provider> findById(UUID id);
    Provider save(Provider provider);
}