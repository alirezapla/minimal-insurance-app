package com.example.insurance.service;

import com.example.insurance.model.Quote;
import com.example.insurance.service.abstraction.IQuoteVisitor;

public class DiscountVisitor implements IQuoteVisitor {
    private final double discount;

    public DiscountVisitor(double discount) {
        this.discount = discount;
    }

    @Override
    public void visit(Quote quote) {
        quote.setPrice(quote.getPrice() * (1 - discount));
    }
}