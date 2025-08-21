package com.example.insurance.application.service.abstraction;

public interface ProviderServiceUseCase {
    void createProviderIfNotExists(String providerName);
}
