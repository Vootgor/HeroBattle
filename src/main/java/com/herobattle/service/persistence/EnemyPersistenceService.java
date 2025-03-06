package com.herobattle.service.persistence;

import com.herobattle.mapper.EnemyMapper;
import com.herobattle.repository.EnemyRepository;
import com.herobattle.repository.entity.EnemyEntity;
import com.herobattle.service.model.Enemy;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
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
            .orElseThrow(() -> new EntityNotFoundException("Hero not found with id: " + enemyId));
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

}
