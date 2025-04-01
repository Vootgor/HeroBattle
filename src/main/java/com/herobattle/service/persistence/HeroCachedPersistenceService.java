package com.herobattle.service.persistence;

import com.herobattle.service.model.Hero;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroCachedPersistenceService implements HeroPersistence {

    private final HeroPersistenceService heroPersistenceService;
    private final RedisTemplate<String, Hero> redisTemplate;
    private static final String HERO_KEY_PREFIX = "hero:";

    @Override
    public Hero save(Hero hero) {
        Hero savedHero = heroPersistenceService.save(hero);
        redisTemplate.opsForValue().set(HERO_KEY_PREFIX + savedHero.getId(), savedHero);
        return savedHero;
    }

    @Override
    public void deleteById(UUID heroId) {
        redisTemplate.delete(HERO_KEY_PREFIX + heroId);
        heroPersistenceService.deleteById(heroId);
    }

    @Override
    public Hero findById(UUID heroId) {
        String key = HERO_KEY_PREFIX + heroId;
        Hero cachedHero = redisTemplate.opsForValue().get(key);
        if (cachedHero != null) {
            System.out.println("use cache to get hero " + heroId);
            return cachedHero;
        }
        System.out.println("searching in db, hero " + heroId);
        Hero hero = heroPersistenceService.findById(heroId);
        redisTemplate.opsForValue().set(key, hero);
        return hero;
    }

    // как есть
    @Override
    public List<Hero> findAll() {
        return heroPersistenceService.findAll();
    }
}
