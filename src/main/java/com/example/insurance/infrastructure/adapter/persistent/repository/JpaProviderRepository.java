package com.example.insurance.infrastructure.adapter.persistent.repository;

import com.example.insurance.infrastructure.adapter.persistent.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaProviderRepository extends JpaRepository<ProviderEntity, Long> {
    Optional<ProviderEntity> findByName(String name);
    Optional<ProviderEntity> findByUuid(UUID uuid);
}