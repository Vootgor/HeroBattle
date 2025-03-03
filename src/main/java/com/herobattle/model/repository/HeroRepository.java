package com.herobattle.model.repository;

import com.herobattle.model.entity.HeroEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {
}
