package com.herobattle.repository;

import com.herobattle.repository.entity.EnemyEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnemyRepository extends JpaRepository<EnemyEntity, UUID> {

    @Query(value = "SELECT * FROM enemies ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<EnemyEntity> findRandomEnemies(@Param("limit") int limit);
}
