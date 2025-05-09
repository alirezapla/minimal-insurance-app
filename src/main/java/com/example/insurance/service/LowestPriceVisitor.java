package com.example.insurance.service;

import com.example.insurance.model.Quote;
import com.example.insurance.service.abstraction.IQuoteVisitor;

public class LowestPriceVisitor implements IQuoteVisitor {
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