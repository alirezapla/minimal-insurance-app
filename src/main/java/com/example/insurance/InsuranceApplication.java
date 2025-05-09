package com.example.insurance;

import com.example.insurance.model.Provider;
import com.example.insurance.repository.ProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class InsuranceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedProviders(ProviderRepository providerRepository) {
		return args -> {
			if (providerRepository.count() == 0) {
				providerRepository.save(new Provider("ProviderA"));
				providerRepository.save(new Provider("ProviderB"));
				providerRepository.save(new Provider("ProviderC"));
			}
		};
	}
}
