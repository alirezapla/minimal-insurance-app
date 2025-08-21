package com.example.insurance.domain.model;

import com.example.insurance.application.service.abstraction.QuoteVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quote implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String coverageType;
    private Double price;
    private String providerName;

    public Quote(UUID id){
        this.id = id;
    }

    public void accept(QuoteVisitor visitor) {
        visitor.visit(this);
    }
}
