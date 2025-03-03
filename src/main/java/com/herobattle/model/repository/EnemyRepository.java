package com.herobattle.model.repository;

import com.herobattle.model.entity.EnemyEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemyRepository extends JpaRepository<EnemyEntity, UUID> {
}
