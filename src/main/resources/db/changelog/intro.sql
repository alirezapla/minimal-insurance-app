CREATE SCHEMA IF NOT EXISTS insurance;
SET search_path TO insurance;

CREATE TABLE provider (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          uuid UUID NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP,
                          created_by VARCHAR(100),
                          updated_by VARCHAR(100)
);


CREATE TABLE quote (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       uuid UUID NOT NULL UNIQUE,
                       coverage_type VARCHAR(255) NOT NULL,
                       price NUMERIC(19,2) NOT NULL,
                       provider_id BIGINT REFERENCES provider(id),
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP,
                       created_by VARCHAR(100),
                       updated_by VARCHAR(100)
);
