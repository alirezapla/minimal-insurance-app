package com.example.insurance;

import com.example.insurance.application.port.abstraction.ProviderServiceUseCase;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.slf4j.Logger;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class InsuranceApplication {

    private static final Logger log = LoggerFactory.getLogger(InsuranceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class, args);
        log.info(".................. Insurance Application started successfully ..................");
    }

    @Bean
    public CommandLineRunner seedProviders(ProviderServiceUseCase providerService) {
        return args -> {
            providerService.createProviderIfNotExists("ProviderA");
            providerService.createProviderIfNotExists("ProviderB");
            providerService.createProviderIfNotExists("ProviderC");
        };
    }
}
