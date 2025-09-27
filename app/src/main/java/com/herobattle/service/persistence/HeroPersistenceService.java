package com.herobattle.service.persistence;

import com.herobattle.exception.HeroNotFoundException;
import com.herobattle.mapper.HeroMapper;
import com.herobattle.repository.HeroRepository;
import com.herobattle.repository.entity.HeroEntity;
import com.herobattle.service.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroPersistenceService implements HeroPersistence {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public Hero save(Hero hero) {
        HeroEntity heroEntity = heroMapper.mapToEntity(hero);
        return heroMapper.mapToModel(heroRepository.save(heroEntity));
    }

    public void deleteById(UUID heroId) {
        heroRepository.deleteById(heroId);
    }

    public Hero findById(UUID heroId) {
        HeroEntity entity = heroRepository.findById(heroId)
                .orElseThrow(() -> new HeroNotFoundException(heroId));
        return heroMapper.mapToModel(entity);
    }

    public List<Hero> findAll() {
        return heroRepository.findAll().stream()
                .map(heroMapper::mapToModel)
                .toList();
    }
}
