package com.herobattle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.herobattle.service.model.BattleLog;
import com.herobattle.service.model.Enemy;
import com.herobattle.service.model.Hero;
import com.herobattle.service.persistence.EnemyPersistenceService;
import com.herobattle.service.persistence.HeroPersistenceService;
import jakarta.persistence.EntityNotFoundException;
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
            "HP героя после боя с тремя врагами должно быть 145");
    }

    @Test
    void should_heroWinWithCorrectLogAndHp_when_fightingThreeEnemies() {
        UUID heroId = UUID.randomUUID();
        Hero hero = new Hero(heroId, "Hero", 150, 15);

        List<Enemy> enemies = List.of(
            new Enemy(UUID.randomUUID(), "Enemy1", 15, 10),
            new Enemy(UUID.randomUUID(), "Enemy2", 15, 10),
            new Enemy(UUID.randomUUID(), "Enemy3", 15, 10)
        );

        List<String> expectedActionLog = List.of(
            "Enemy1 атакует Hero и наносит 10 урона, у героя остается 140 HP!!",
            "Hero атакует Enemy1 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy2 атакует Hero и наносит 10 урона, у героя остается 130 HP!!",
            "Hero атакует Enemy2 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy3 атакует Hero и наносит 10 урона, у героя остается 120 HP!!",
            "Hero атакует Enemy3 и наносит 15 урона, у врага остается 0 HP!!"
        );
        BattleLog expectedLog = new BattleLog(expectedActionLog, "Герой победил всех!");

        when(heroPersistenceService.findById(heroId)).thenReturn(hero);
        when(enemyPersistenceService.getRandomEnemiesAmountFromTo(3, 3)).thenReturn(enemies);
        BattleLog logResult = battleService.fight(heroId, 3, 3);

        assertEquals(expectedLog, logResult);
        assertEquals(120, hero.getHp(),
            "HP героя после боя с тремя врагами должно быть 120");
    }

    @Test
    void should_heroLossWithCorrectLogAndHp_when_fightingThreeEnemies() {
        UUID heroId = UUID.randomUUID();
        Hero hero = new Hero(heroId, "Hero", 30, 15);

        List<Enemy> enemies = List.of(
            new Enemy(UUID.randomUUID(), "Enemy1", 15, 10),
            new Enemy(UUID.randomUUID(), "Enemy2", 15, 10),
            new Enemy(UUID.randomUUID(), "Enemy3", 15, 10)
        );

        List<String> expectedActionLog = List.of(
            "Enemy1 атакует Hero и наносит 10 урона, у героя остается 20 HP!!",
            "Hero атакует Enemy1 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy2 атакует Hero и наносит 10 урона, у героя остается 10 HP!!",
            "Hero атакует Enemy2 и наносит 15 урона, у врага остается 0 HP!!",
            "Enemy3 атакует Hero и наносит 10 урона, у героя остается 0 HP!!"
        );
        BattleLog expectedLog = new BattleLog(expectedActionLog, "Герой мертв");

        when(heroPersistenceService.findById(heroId)).thenReturn(hero);
        when(enemyPersistenceService.getRandomEnemiesAmountFromTo(3, 3)).thenReturn(enemies);
        BattleLog logResult = battleService.fight(heroId, 3, 3);

        assertEquals(expectedLog, logResult);
        assertEquals(0, hero.getHp(),
            "HP героя после боя с тремя врагами должно быть 0");
    }

    @Test
    void should_throwEntityNotFoundException_when_heroNotFound() {
        UUID heroId = UUID.randomUUID();
        when(heroPersistenceService.findById(heroId))
            .thenThrow(new EntityNotFoundException("Hero not found with id: " + heroId));

        assertThrows(EntityNotFoundException.class, () ->
            battleService.fight(heroId, 1, 1));

        verify(heroPersistenceService, times(1)).findById(heroId);
    }

    @Test
    void should_throwIllegalArgumentException_when_minEnemiesMoreMaxEnemies() {
        UUID heroId = UUID.randomUUID();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            battleService.fight(heroId, 3, 2));
        assertEquals("minEnemies cannot exceed maxEnemies", exception.getMessage());
    }

    @Test
    void should_throwIllegalArgumentException_when_minEnemiesIsZero() {
        UUID heroId = UUID.randomUUID();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            battleService.fight(heroId, 0, 2));
        assertEquals("minEnemies cannot be less than 0", exception.getMessage());
    }


}