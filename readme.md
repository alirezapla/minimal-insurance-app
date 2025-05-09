
# Minimal Insurance Quote App 🛡️

A minimal Spring Boot RESTful application to manage and aggregate insurance quotes from multiple providers with support for dynamic discount strategies using the Visitor pattern, caching (Hazelcast), AOP-based logging, Spring Security, and GitHub Actions CI/CD.

## Features

- CRUD APIs for insurance quotes
- Aggregation endpoint (e.g. best quote by price)
- Visitor pattern for flexible quote evaluation
- Hazelcast caching with eviction strategies
- AOP-based logging (controller & service layer)
- Spring Security (secured API access)
- Seeded H2 in-memory database
- Postman collection
- Unit tests
- CI/CD with GitHub Actions

---

## Setup & Run

### Requirements

- Java 17+
- Maven 3+

### Run locally

```bash
git clone https://github.com/alirezapla/minimal-insurance-app.git
cd minimal-insurance-app
```
```bash
./mvnw spring-boot:run
```
or
```bash
docker compose up
```
****

## API Endpoints

|HTTP Method	|Endpoint	|Description|
| :---: | :---: | :---: |
|GET|	/quotes|	List all quotes|
|GET|	/quotes/{id}|	Get a quote by ID|
|POST|	/quotes|	Create a new quote|
|PUT|	/quotes/{id}|	Update a quote|
|DELETE|	/quotes/{id}|	Delete a quote|
|GET|	/quotes/aggregate|	Get the best/sorted quote(s)|


### Visitor Pattern for Quote Evaluation

Custom strategies can be added via IQuoteVisitor to dynamically compute discounts, aggregations, or filter logic.


```java
public class LowestPriceVisitor implements IQuoteVisitor {
// Implements logic to find best quote based on price
}
```

### Sample Data (H2 DB)

Providers are auto-seeded at startup via `CommandLineRunner`:


### Postman Collection

Import from: `InsuranceAPI.postman_collection.json`

Includes all CRUD and aggregation endpoints with sample payloads.