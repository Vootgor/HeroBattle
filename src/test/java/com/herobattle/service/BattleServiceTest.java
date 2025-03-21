package com.herobattle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.herobattle.exception.HeroNotFoundException;
import com.herobattle.exception.MinBelowZeroException;
import com.herobattle.exception.MinExceedsMaxException;
import com.herobattle.service.model.BattleLog;
import com.herobattle.service.model.Enemy;
import com.herobattle.service.model.Hero;
import com.herobattle.service.persistence.EnemyPersistenceService;
import com.herobattle.service.persistence.HeroPersistenceService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleServiceTest {

    @Mock
    private HeroPersistenceService heroPersistenceService;
    @Mock
    private EnemyPersistenceService enemyPersistenceService;
    @InjectMocks
    private BattleService battleService;


    @Test
    void should_logMonsterAttackFirstAndUpdateHeroHp_when_fightingOneEnemy() {
        UUID heroId = UUID.randomUUID();
        Hero hero = new Hero(heroId, "Hero", 150, 15);

        UUID enemyId = UUID.randomUUID();
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(enemyId, "Enemy", 15, 5));

        when(heroPersistenceService.findById(heroId)).thenReturn(hero);
        when(enemyPersistenceService.getRandomEnemiesAmountFromTo(1, 1)).thenReturn(enemies);

        BattleLog LogResult = battleService.fight(heroId, 1, 1);

        assertEquals("Enemy атакует Hero и наносит 5 урона, у героя остается 145 HP!!"
            , LogResult.actionLog().getFirst());
        assertEquals(145, hero.getHp(),
            "HP героя после боя с одним врагом должно быть 145");
    }

    @Test
    void should_heroWinWithCorrectLogAndHp_when_fightingThreeEnemies() {
        UUID heroId = UUID.randomUUID();
        Hero hero = new Hero(heroId, "Hero", 150, 15);

        List<Enemy> enemies = List.of(
            new Enemy(UUID.randomUUID(), "Enemy1", 15, 50),
            new Enemy(UUID.randomUUID(), "Enemy2", 30, 20),
            new Enemy(UUID.randomUUID(), "Enemy3", 60, 10)
        );

        List<String> expectedActionLog = List.of(
            "Enemy1 атакует Hero и наносит 50 урона, у героя остается 100 HP!!",
            "Hero атакует Enemy1 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy2 атакует Hero и наносит 20 урона, у героя остается 80 HP!!",
            "Hero атакует Enemy2 и наносит 15 урона, у врага остается 15 HP!!",
            "Enemy2 атакует Hero и наносит 20 урона, у героя остается 60 HP!!",
            "Hero атакует Enemy2 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy3 атакует Hero и наносит 10 урона, у героя остается 50 HP!!",
            "Hero атакует Enemy3 и наносит 15 урона, у врага остается 45 HP!!",
            "Enemy3 атакует Hero и наносит 10 урона, у героя остается 40 HP!!",
            "Hero атакует Enemy3 и наносит 15 урона, у врага остается 30 HP!!",
            "Enemy3 атакует Hero и наносит 10 урона, у героя остается 30 HP!!",
            "Hero атакует Enemy3 и наносит 15 урона, у врага остается 15 HP!!",
            "Enemy3 атакует Hero и наносит 10 урона, у героя остается 20 HP!!",
            "Hero атакует Enemy3 и наносит 15 урона, у врага остается 0 HP!!"
            );
        BattleLog expectedLog = new BattleLog(expectedActionLog, "Герой победил всех!");

        when(heroPersistenceService.findById(heroId)).thenReturn(hero);
        when(enemyPersistenceService.getRandomEnemiesAmountFromTo(3, 3)).thenReturn(enemies);
        BattleLog logResult = battleService.fight(heroId, 3, 3);

        assertEquals(expectedLog, logResult);
        assertEquals(20, hero.getHp(),
            "HP героя после боя с тремя врагами должно быть 20");
    }

    @Test
    void should_heroLossWithCorrectLogAndHp_when_fightingThreeEnemies() {
        UUID heroId = UUID.randomUUID();
        Hero hero = new Hero(heroId, "Hero", 100, 15);

        List<Enemy> enemies = List.of(
            new Enemy(UUID.randomUUID(), "Enemy1", 25, 20),
            new Enemy(UUID.randomUUID(), "Enemy2", 30, 15),
            new Enemy(UUID.randomUUID(), "Enemy3", 5, 50)
        );

        List<String> expectedActionLog = List.of(
            "Enemy1 атакует Hero и наносит 20 урона, у героя остается 80 HP!!",
            "Hero атакует Enemy1 и наносит 15 урона, у врага остается 10 HP!!",
            "Enemy1 атакует Hero и наносит 20 урона, у героя остается 60 HP!!",
            "Hero атакует Enemy1 и наносит 15 урона, у врага остается -5 HP!!",
            "Enemy2 атакует Hero и наносит 15 урона, у героя остается 45 HP!!",
            "Hero атакует Enemy2 и наносит 15 урона, у врага остается 15 HP!!",
            "Enemy2 атакует Hero и наносит 15 урона, у героя остается 30 HP!!",
            "Hero атакует Enemy2 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy3 атакует Hero и наносит 50 урона, у героя остается -20 HP!!"
        );
        BattleLog expectedLog = new BattleLog(expectedActionLog, "Герой мертв");

        when(heroPersistenceService.findById(heroId)).thenReturn(hero);
        when(enemyPersistenceService.getRandomEnemiesAmountFromTo(3, 3)).thenReturn(enemies);
        BattleLog logResult = battleService.fight(heroId, 3, 3);

        assertEquals(expectedLog, logResult);
        assertEquals(-20, hero.getHp(),
            "HP героя после боя с тремя врагами должно быть -20");
    }

    @Test
    void should_throwHeroNotFoundException_when_heroNotFound() {
        UUID heroId = UUID.randomUUID();
        when(heroPersistenceService.findById(heroId))
            .thenThrow(new HeroNotFoundException(heroId));

        HeroNotFoundException exception = assertThrows(HeroNotFoundException.class, () ->
            battleService.fight(heroId, 1, 1));

        assertEquals("Hero not found", exception.getError());
        assertEquals("Hero not found with id: " + heroId, exception.getMessage());

        verify(heroPersistenceService, times(1)).findById(heroId);
    }

    @Test
    void should_throwMinExceedsMaxException_when_minEnemiesMoreMaxEnemies() {
        UUID heroId = UUID.randomUUID();

        MinExceedsMaxException exception = assertThrows(MinExceedsMaxException.class, () ->
            battleService.fight(heroId, 3, 2));
        assertEquals("minEnemies cannot exceed maxEnemies", exception.getMessage());
        assertEquals("User input error", exception.getError());
    }

    @Test
    void should_throwMinBelowZeroException_when_minEnemiesIsZero() {
        UUID heroId = UUID.randomUUID();

        MinBelowZeroException exception = assertThrows(MinBelowZeroException.class, () ->
            battleService.fight(heroId, 0, 2));
        assertEquals("minEnemies cannot be less than 0", exception.getMessage());
        assertEquals("User input error", exception.getError());
    }


}