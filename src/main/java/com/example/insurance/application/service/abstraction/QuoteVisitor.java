package com.example.insurance.application.service.abstraction;


import com.example.insurance.domain.model.Quote;

public interface QuoteVisitor {
    void visit(Quote quote);
}
