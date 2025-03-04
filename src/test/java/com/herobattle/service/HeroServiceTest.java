package com.herobattle.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.herobattle.controller.dto.HeroDto;
import com.herobattle.mapper.HeroMapper;
import com.herobattle.repository.HeroRepository;
import com.herobattle.repository.entity.HeroEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private HeroMapper heroMapper;

    @InjectMocks
    private HeroService heroService;

    private UUID heroId;
    private HeroEntity hero;
    private HeroDto heroDto;

    @BeforeEach
    void setUp() {
        heroId = UUID.randomUUID();
        hero = new HeroEntity(heroId, "Superman",100, 10);
        heroDto = new HeroDto(heroId, "Superman", 100, 10);
    }

    @Test
    void getHero_ShouldReturnHeroDto_WhenHeroExists() {
        when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));
        when(heroMapper.mapToDto(hero)).thenReturn(heroDto);

        HeroDto result = heroService.getHero(heroId);

        assertNotNull(result);
        assertEquals(heroDto.id(), result.id());
        assertEquals(heroDto.name(), result.name());
        verify(heroRepository, times(1)).findById(heroId);
        verify(heroMapper, times(1)).mapToDto(hero);
    }

    @Test
    void getHero_ShouldThrowEntityNotFoundException_WhenHeroDoesNotExist() {
        when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                heroService.getHero(heroId)
        );

        assertEquals("Hero not found with id: " + heroId, exception.getMessage());
        verify(heroRepository, times(1)).findById(heroId);
        verifyNoInteractions(heroMapper);
    }
}