package com.example.insurance.infrastructure.adapter.persistent.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "provider",schema = "insurance")
public class ProviderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public ProviderEntity() {
    }

    public ProviderEntity(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

}