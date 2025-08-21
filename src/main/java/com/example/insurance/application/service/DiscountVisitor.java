package com.example.insurance.application.service;

import com.example.insurance.application.port.abstraction.QuoteVisitor;
import com.example.insurance.domain.model.Quote;

public class DiscountVisitor implements QuoteVisitor {
    private final double discount;

    public DiscountVisitor(double discount) {
        this.discount = discount;
    }

    @Override
    public void visit(Quote quote) {
        quote.setPrice(quote.getPrice() * (1 - discount));
    }
}