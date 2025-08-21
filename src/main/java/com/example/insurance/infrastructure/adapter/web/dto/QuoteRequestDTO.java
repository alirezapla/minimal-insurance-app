package com.example.insurance.infrastructure.adapter.web.dto;


public record QuoteRequestDTO(
        String coverageType,
        Double price,
        String providerName
) {}