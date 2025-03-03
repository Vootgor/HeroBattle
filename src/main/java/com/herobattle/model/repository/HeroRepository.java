package com.herobattle.model.repository;

import com.herobattle.model.entity.Hero;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, UUID> {
}
