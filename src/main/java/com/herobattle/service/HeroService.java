package com.herobattle.service;

import com.herobattle.controller.dto.HeroDto;
import com.herobattle.controller.request.CreateHeroRequest;
import com.herobattle.mapper.HeroMapper;
import com.herobattle.repository.HeroRepository;
import com.herobattle.repository.entity.HeroEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public HeroDto createHero(CreateHeroRequest request) {
        HeroEntity entity = heroMapper.mapToEntity(request);
        return heroMapper.mapToDto(heroRepository.save(entity));
    }

    public HeroDto getHero(UUID heroId) {
        return heroMapper.mapToDto(heroRepository.findById(heroId)
            .orElseThrow(() -> new EntityNotFoundException("Hero not found with id: " + heroId)));
    }

    public HeroDto deleteHero(UUID heroId) {
        HeroEntity entity = heroRepository.findById(heroId)
            .orElseThrow(() -> new EntityNotFoundException("Hero not found with id: " + heroId));
        heroRepository.deleteById(heroId);
        return heroMapper.mapToDto(entity);
    }

    public List<HeroDto> getAllHeroes() {
        return heroRepository.findAll().stream()
            .map(heroMapper::mapToDto)
            .toList();
    }
}
