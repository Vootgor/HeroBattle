package com.herobattle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.herobattle.controller.dto.HeroDto;
import com.herobattle.mapper.HeroMapper;
import com.herobattle.repository.HeroRepository;
import com.herobattle.repository.entity.HeroEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private HeroMapper heroMapper;

    @InjectMocks
    private HeroService heroService;

    private UUID heroId;
    private HeroEntity heroEntity;
    private HeroDto heroDto;

    @BeforeEach
    void setUp() {
        heroId = UUID.randomUUID();
        heroEntity = new HeroEntity();
        heroEntity.setId(heroId);
        heroEntity.setName("Hero");
        heroEntity.setHp(101);
        heroEntity.setBaseDamage(15);

        heroDto = new HeroDto(heroId, "Hero", 101, 15);
    }

    @Test
    void shouldDeleteHero_WhenHeroExists() {
        when(heroRepository.findById(heroId)).thenReturn(Optional.of(heroEntity));
        when(heroMapper.mapToDto(heroEntity)).thenReturn(heroDto);

        HeroDto result = heroService.deleteHero(heroId);

        assertEquals(heroDto, result);
        verify(heroRepository).deleteById(heroId);
        verify(heroRepository, times(1)).findById(heroId);
        verify(heroMapper, times(1)).mapToDto(heroEntity);
    }

    @Test
    void shouldThrowEntityNotFound_WhenHeroDoesNotExist() {
        UUID heroId = UUID.randomUUID();
        when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class
            , () -> heroService.deleteHero(heroId));
        assertEquals("Hero not found with id: " + heroId, exception.getMessage());
        verify(heroRepository, times(1)).findById(heroId);
        verify(heroRepository, never()).deleteById(heroId);
        verify(heroMapper, never()).mapToDto(any());
    }
}
