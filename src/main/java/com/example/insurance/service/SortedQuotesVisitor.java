package com.example.insurance.service;

import com.example.insurance.model.Quote;
import com.example.insurance.service.abstraction.IQuoteVisitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedQuotesVisitor implements IQuoteVisitor {
    private List<Quote> quotes = new ArrayList<>();

    @Override
    public void visit(Quote quote) {
        quotes.add(quote);
    }

    public List<Quote> getSortedQuotesAsc() {
        return quotes.stream()
                .sorted(Comparator.comparingDouble(Quote::getPrice))
                .collect(Collectors.toList());
    }

    public List<Quote> getSortedQuotesDesc() {
        return quotes.stream()
                .sorted(Comparator.comparingDouble(Quote::getPrice).reversed())
                .collect(Collectors.toList());
    }
}
