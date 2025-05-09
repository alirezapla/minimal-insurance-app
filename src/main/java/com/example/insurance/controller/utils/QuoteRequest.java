package com.example.insurance.controller.utils;

import lombok.Getter;

@Getter
public class QuoteRequest {
    private final String coverageType;
    private final Double price;
    private final String providerName;

    public QuoteRequest(String coverageType, Double price, String providerName) {
        this.coverageType = coverageType;
        this.price = price;
        this.providerName = providerName;
    }
}