package com.herobattle.configuration;

import com.herobattle.service.HeroService;
import com.herobattle.service.persistence.HeroCachedPersistenceService;
import com.herobattle.service.persistence.HeroPersistenceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroServiceConfig {

    @Bean
    @ConditionalOnProperty(name = "hero-service.use-cache", havingValue = "true", matchIfMissing = false)
    public HeroService heroServiceWithCache(HeroCachedPersistenceService persistenceService) {
        return new HeroService(persistenceService);
    }

    @Bean
    @ConditionalOnProperty(name = "hero-service.use-cache", havingValue = "false", matchIfMissing = true)
    public HeroService heroServiceWithoutCache(HeroPersistenceService persistenceService) {
        return new HeroService(persistenceService);
    }
}
