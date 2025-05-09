package com.example.insurance.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
public class Provider implements Serializable {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;

    public Provider() {}

    public Provider(String name) {
        this.name = name;
    }
}