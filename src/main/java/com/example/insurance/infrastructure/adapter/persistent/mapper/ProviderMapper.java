package com.example.insurance.infrastructure.adapter.persistent.mapper;

import com.example.insurance.domain.model.Provider;
import com.example.insurance.infrastructure.adapter.persistent.entity.ProviderEntity;

public class ProviderMapper {

    public static Provider toDomain(ProviderEntity entity) {
        if (entity == null) return null;
        Provider provider = new Provider();
        provider.setId(entity.getUuid());
        provider.setName(entity.getName());
        return provider;
    }

    public static ProviderEntity toEntity(Provider domain) {
        if (domain == null) return null;
        ProviderEntity entity = new ProviderEntity(domain.getName());
        return entity;
    }
}
