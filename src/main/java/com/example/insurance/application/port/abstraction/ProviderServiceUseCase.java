package com.example.insurance.application.port.abstraction;

public interface ProviderServiceUseCase {
    void createProviderIfNotExists(String providerName);
}
