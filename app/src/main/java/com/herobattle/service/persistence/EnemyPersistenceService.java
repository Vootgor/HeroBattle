package com.herobattle.service.persistence;

import com.herobattle.exception.EnemyNotFoundException;
import com.herobattle.mapper.EnemyMapper;
import com.herobattle.repository.EnemyRepository;
import com.herobattle.repository.entity.EnemyEntity;
import com.herobattle.service.model.Enemy;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnemyPersistenceService {

    private final EnemyRepository enemyRepository;
    private final EnemyMapper enemyMapper;

    public Enemy save(Enemy enemy) {
        EnemyEntity entity = enemyMapper.mapToEntity(enemy);
        return enemyMapper.mapToModel(enemyRepository.save(entity));
    }

    public Enemy findById(UUID enemyId) {
        EnemyEntity entity = enemyRepository.findById(enemyId)
            .orElseThrow(() -> new EnemyNotFoundException(enemyId));
        return enemyMapper.mapToModel(entity);
    }

    public void deleteById(UUID enemyId) {
        enemyRepository.deleteById(enemyId);
    }

    public List<Enemy> findAll() {
        return enemyRepository.findAll().stream()
            .map(enemyMapper::mapToModel)
            .toList();
    }

    public List<Enemy> getRandomEnemiesAmountFromTo(int minEnemies, int maxEnemies) {
        int randomNumber = ThreadLocalRandom.current().nextInt(minEnemies, maxEnemies + 1);
        long totalEnemies = enemyRepository.count();
        if (totalEnemies < randomNumber) {
            throw new IllegalArgumentException(
                "Requested enemies exceed available count. Available " + totalEnemies);
        }
        return enemyRepository.findRandomEnemies(randomNumber).stream()
            .map(enemyMapper::mapToModel)
            .toList();
    }
}
