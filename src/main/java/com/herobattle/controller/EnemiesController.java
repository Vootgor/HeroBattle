package com.herobattle.controller;

import com.herobattle.controller.dto.EnemyDto;
import com.herobattle.controller.request.CreateEnemyRequest;
import com.herobattle.mapper.EnemyMapper;
import com.herobattle.service.EnemyService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/enemies")
public class EnemiesController {

    private final EnemyService enemyService;
    private final EnemyMapper enemyMapper;

    @PostMapping("/")
    public EnemyDto createEnemy(@RequestBody CreateEnemyRequest request) {
        return enemyMapper.mapToDto(enemyService.createEnemy(request));
    }

    @DeleteMapping("/{id}")
    public EnemyDto deleteEnemy(@PathVariable UUID id) {
        return enemyMapper.mapToDto(enemyService.deleteEnemy(id));
    }

    @GetMapping("/{id}")
    public EnemyDto getEnemy(@PathVariable UUID id) {
        return enemyMapper.mapToDto(enemyService.getEnemy(id));
    }

    @GetMapping
    public List<EnemyDto> getAllEnemies() {
        return enemyService.getAllEnemies()
            .stream()
            .map(enemyMapper::mapToDto)
            .toList();
    }
}
