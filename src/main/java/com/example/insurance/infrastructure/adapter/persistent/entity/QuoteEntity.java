package com.example.insurance.infrastructure.adapter.persistent.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "quote",schema = "insurance")
public class QuoteEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coverageType;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderEntity provider;

    public QuoteEntity() {
    }

    public QuoteEntity(String coverageType, Double price, ProviderEntity provider) {
        this.coverageType = coverageType;
        this.price = price;
        this.provider = provider;
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

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

}
