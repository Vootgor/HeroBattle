package com.herobattle.service;

import com.herobattle.controller.request.CreateHeroRequest;
import com.herobattle.service.model.Hero;

import com.herobattle.service.persistence.HeroPersistence;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeroService {

    private final HeroPersistence heroPersistence;

    public Hero createHero(CreateHeroRequest request) {
        return heroPersistence.save(
                Hero.builder()
                        .name(request.name())
                        .hp(request.hp())
                        .baseDamage(request.baseDamage())
                        .build()
        );
    }

    public Hero getHero(UUID heroId) {
        return heroPersistence.findById(heroId);
    }

    public Hero deleteHero(UUID heroId) {
        Hero model = heroPersistence.findById(heroId);
        heroPersistence.deleteById(heroId);
        return model;
    }

    public List<Hero> getAllHeroes() {
        return heroPersistence.findAll();
    }
}
