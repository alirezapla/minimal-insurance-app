package com.example.insurance.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Provider implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String name;
}
