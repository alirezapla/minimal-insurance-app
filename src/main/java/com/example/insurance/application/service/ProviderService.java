package com.example.insurance.application.service;

import com.example.insurance.InsuranceApplication;
import com.example.insurance.application.service.abstraction.ProviderServiceUseCase;
import com.example.insurance.domain.model.Provider;
import com.example.insurance.domain.port.secondary.ProviderRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProviderService implements ProviderServiceUseCase {
    private static final Logger log = LoggerFactory.getLogger(ProviderService.class);

    private final ProviderRepositoryPort providerRepository;

    public ProviderService(ProviderRepositoryPort providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public void createProviderIfNotExists(String providerName) {
//        providerRepository.findByName(providerName)
//                .orElseGet(() -> {
//                    Provider provider = new Provider();
//                    provider.setName(providerName);
//                    return providerRepository.save(provider);
//                });
        providerRepository.findByName(providerName).ifPresentOrElse(
                p -> log.info("Provider '{}' already exists (id={})", providerName, p.getId()),
                () -> {
                    Provider provider = new Provider();
                    provider.setName(providerName);
                    providerRepository.save(provider);
                    log.info("Provider '{}' created ✅", providerName);
                }
        );
    }
}
