package com.herobattle.service;

import com.herobattle.exception.MinBelowZeroException;
import com.herobattle.exception.MinExceedsMaxException;
import com.herobattle.service.model.BattleLog;
import com.herobattle.service.model.Enemy;
import com.herobattle.service.model.Hero;
import com.herobattle.service.model.NarratorComments;
import com.herobattle.service.persistence.EnemyPersistenceService;
import com.herobattle.service.persistence.HeroPersistenceService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattleService {

    private final HeroPersistenceService heroPersistenceService;
    private final EnemyPersistenceService enemyPersistenceService;
    private final NarratorService narratorService;
    private NarratorComments narratorComments;

    public BattleLog fight(UUID heroId, int minEnemies, int maxEnemies) {
        if (minEnemies > maxEnemies) {
            throw new MinExceedsMaxException();
        }
        if (minEnemies <= 0) {
            throw new MinBelowZeroException();
        }

        Hero hero = heroPersistenceService.findById(heroId);
        List<Enemy> enemies = enemyPersistenceService.getRandomEnemiesAmountFromTo(minEnemies,
            maxEnemies);
        narratorComments = narratorService.getNarratorComments(
            hero.getName()
            , "weapon"
            , "background"
            , "clothes"
        );
        var actionLog = new ArrayList<String>();
        actionLog.add(narratorComments.onIntroduction());

        for (Enemy enemy : enemies) {
            actionLog.addAll(fightWith(hero, enemy));
            if (hero.getHp() <= 0) {
                actionLog.add(narratorComments.onDefeat());
                return new BattleLog(actionLog, "Герой мертв");
            }
        }
        actionLog.add(narratorComments.onWin());
        return new BattleLog(actionLog, "Герой победил всех!");
    }

    private List<String> fightWith(Hero hero, Enemy enemy) {
        var actionLog = new ArrayList<String>();

        while (hero.getHp() > 0 && enemy.getHp() > 0) {
            int enemyDamage = enemyAttack(hero, enemy);
            actionLog.add("%s атакует %s и наносит %d урона, у героя остается %d HP!!".formatted(
                enemy.getName(), hero.getName(), enemyDamage, hero.getHp()));
            if (hero.getHp() <= 0) break;

            int heroDamage = heroAttack(hero, enemy);
            actionLog.add(narratorComments.onAttack());
            actionLog.add("%s атакует %s и наносит %d урона, у врага остается %d HP!!".formatted(
                hero.getName(), enemy.getName(), heroDamage, enemy.getHp()));
            if (enemy.getHp() <= 0) break;
        }
        return actionLog;
    }

    private int enemyAttack(Hero hero, Enemy enemy) {
        int damage = enemy.getBaseDamage();
        hero.setHp(hero.getHp() - damage);
        return damage;
    }

    private int heroAttack(Hero hero, Enemy enemy) {
        int damage = hero.getBaseDamage();
        enemy.setHp(enemy.getHp() - damage);
        return damage;
    }
}
