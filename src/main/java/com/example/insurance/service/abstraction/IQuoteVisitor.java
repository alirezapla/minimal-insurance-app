package com.example.insurance.service.abstraction;

import com.example.insurance.model.Quote;

public interface IQuoteVisitor {
    void visit(Quote quote);
}
