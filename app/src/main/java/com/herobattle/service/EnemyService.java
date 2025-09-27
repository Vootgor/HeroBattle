package com.herobattle.service;

import com.herobattle.controller.request.CreateEnemyRequest;
import com.herobattle.service.model.Enemy;
import com.herobattle.service.persistence.EnemyPersistenceService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnemyService {

    private final EnemyPersistenceService persistenceService;


    public Enemy createEnemy(CreateEnemyRequest request) {
        return persistenceService.save(Enemy.builder()
            .hp(request.hp())
            .name(request.name())
            .baseDamage(request.baseDamage())
            .build());
    }

    public Enemy getEnemy(UUID enemyId) {
        return persistenceService.findById(enemyId);
    }

    public Enemy deleteEnemy(UUID enemyId) {
        Enemy model = persistenceService.findById(enemyId);
        persistenceService.deleteById(enemyId);
        return model;
    }

    public List<Enemy> getAllEnemies() {
        return persistenceService.findAll();
    }
}
