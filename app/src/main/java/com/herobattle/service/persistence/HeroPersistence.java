package com.herobattle.service.persistence;

import com.herobattle.service.model.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroPersistence {
    Hero save(Hero hero);
    void deleteById(UUID heroId);
    Hero findById(UUID heroId);
    List<Hero> findAll();
}
