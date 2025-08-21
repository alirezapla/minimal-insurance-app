package com.example.insurance.application.port.abstraction;


import com.example.insurance.domain.model.Quote;

public interface QuoteVisitor {
    void visit(Quote quote);
}
