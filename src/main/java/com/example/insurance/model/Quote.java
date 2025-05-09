package com.example.insurance.model;

import com.example.insurance.service.abstraction.IQuoteVisitor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Quote implements Serializable {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String coverageType;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Quote() {}

    public Quote(String coverageType, Double price, Provider provider) {
        this.coverageType = coverageType;
        this.price = price;
        this.provider = provider;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void accept(IQuoteVisitor visitor)
    {
        visitor.visit(this);
    }
}
