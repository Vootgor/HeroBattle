package com.herobattle.controller;

import com.herobattle.service.BattleService;
import com.herobattle.service.model.BattleLog;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/battles")
public class BattleController {

    private final BattleService battleService;

    @GetMapping("/fight/hero/{id}")
    public BattleLog fight(@PathVariable UUID id
        , @RequestParam Integer minEnemies
        , @RequestParam Integer maxEnemies) {

        return battleService.fight(id, minEnemies, maxEnemies);
    }
}
