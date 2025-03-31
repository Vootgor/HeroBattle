package com.herobattle.service;

import com.herobattle.controller.request.CreateHeroRequest;
import com.herobattle.service.model.Hero;

import com.herobattle.service.persistence.HeroPersistence;
import java.util.List;
import java.util.UUID;

import com.herobattle.service.persistence.HeroPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroPersistence heroPersistenceService;

    public Hero createHero(CreateHeroRequest request) {
        return heroPersistenceService.save(
                Hero.builder()
                        .name(request.name())
                        .hp(request.hp())
                        .baseDamage(request.baseDamage())
                        .build()
        );
    }

    public Hero getHero(UUID heroId) {
        return heroPersistenceService.findById(heroId);
    }

    public Hero deleteHero(UUID heroId) {
        Hero model = heroPersistenceService.findById(heroId);
        heroPersistenceService.deleteById(heroId);
        return model;
    }

    public List<Hero> getAllHeroes() {
        return heroPersistenceService.findAll();
    }
}
