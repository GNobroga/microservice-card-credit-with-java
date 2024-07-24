package io.github.gnobroga.mscards.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.gnobroga.mscards.service.PopulateDatabaseService;

@Configuration
public class AppConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(PopulateDatabaseService service) {
        return args -> service.initialize();
    }
}
