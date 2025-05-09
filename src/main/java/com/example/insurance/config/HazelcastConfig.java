package com.example.insurance.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setInstanceName("insurance-quotes-cache");

        MapConfig quotesMapConfig = new MapConfig();
        quotesMapConfig.setName("quotes")
                .setTimeToLiveSeconds(120)
                .setMaxIdleSeconds(120);

        MapConfig bestQuoteMapConfig = new MapConfig();
        bestQuoteMapConfig.setName("aggregateQuotes")
                .setTimeToLiveSeconds(60);

        config.addMapConfig(quotesMapConfig);
        config.addMapConfig(bestQuoteMapConfig);

        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }
}