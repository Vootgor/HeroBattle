package com.herobattle.repository;

import com.herobattle.repository.entity.EnemyEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemyRepository extends JpaRepository<EnemyEntity, UUID> {
}
