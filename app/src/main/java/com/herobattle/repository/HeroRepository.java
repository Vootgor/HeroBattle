package com.herobattle.repository;

import com.herobattle.repository.entity.HeroEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {
}
