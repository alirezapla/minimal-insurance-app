package com.example.insurance.application.service;

import com.example.insurance.application.service.abstraction.QuoteVisitor;
import com.example.insurance.domain.model.Quote;

public class LowestPriceVisitor implements QuoteVisitor {
    private Quote bestQuote = null;

    @Override
    public void visit(Quote quote) {
        if (bestQuote == null || quote.getPrice() < bestQuote.getPrice()) {
            bestQuote = quote;
        }
    }

    public Quote getBestQuote() {
        return bestQuote;
    }
}