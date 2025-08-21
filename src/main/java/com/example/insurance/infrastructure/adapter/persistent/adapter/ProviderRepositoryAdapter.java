package com.example.insurance.infrastructure.adapter.persistent.adapter;

import com.example.insurance.domain.model.Provider;
import com.example.insurance.domain.port.secondary.ProviderRepositoryPort;
import com.example.insurance.infrastructure.adapter.persistent.entity.ProviderEntity;
import com.example.insurance.infrastructure.adapter.persistent.mapper.ProviderMapper;
import com.example.insurance.infrastructure.adapter.persistent.repository.JpaProviderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class ProviderRepositoryAdapter implements ProviderRepositoryPort {

    private final JpaProviderRepository jpaProviderRepository;

    public ProviderRepositoryAdapter(JpaProviderRepository jpaProviderRepository) {
        this.jpaProviderRepository = jpaProviderRepository;
    }

    @Override
    public Optional<Provider> findByName(String name) {
        return jpaProviderRepository.findByName(name).map(ProviderMapper::toDomain);
    }

    @Override
    public Optional<Provider> findById(UUID id) {
        return jpaProviderRepository.findByUuid(id).map(ProviderMapper::toDomain);
    }

    @Override
    public Provider save(Provider provider) {
        ProviderEntity savedItem = jpaProviderRepository.save(ProviderMapper.toEntity(provider));
        return ProviderMapper.toDomain(savedItem);
    }
}
