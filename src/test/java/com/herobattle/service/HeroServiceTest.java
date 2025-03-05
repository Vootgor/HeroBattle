package com.herobattle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.herobattle.service.model.Hero;
import com.herobattle.service.persistence.HeroPersistenceService;
import jakarta.persistence.EntityNotFoundException;

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
    private HeroPersistenceService heroPersistenceService;

    @InjectMocks
    private HeroService heroService;

    private UUID heroId;
    private Hero hero;

    @BeforeEach
    void setUp() {
        heroId = UUID.randomUUID();
        hero = new Hero(heroId, "Hero", 101, 15);
    }

    @Test
    void shouldDeleteHero_WhenHeroExists() {
        when(heroPersistenceService.findById(heroId)).thenReturn(hero);

        Hero result = heroService.deleteHero(heroId);

        assertEquals(hero, result);
        verify(heroPersistenceService).deleteById(heroId);
        verify(heroPersistenceService, times(1)).findById(heroId);
    }

    @Test
    void shouldThrowEntityNotFound_WhenHeroDoesNotExist() {
        UUID heroId = UUID.randomUUID();
        when(heroPersistenceService.findById(heroId)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> heroService.deleteHero(heroId));
        verify(heroPersistenceService, times(1)).findById(heroId);
    }
}
