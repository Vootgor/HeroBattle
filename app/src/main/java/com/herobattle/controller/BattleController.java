package com.herobattle.controller;

import com.herobattle.controller.request.FightRequest;
import com.herobattle.service.BattleService;
import com.herobattle.service.model.BattleLog;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/battles")
public class BattleController {

    private final BattleService battleService;

    @GetMapping("/fight/hero/{id}")
    public BattleLog fight(@PathVariable UUID id, @ModelAttribute FightRequest request) {
        return battleService.fight(id, request);
    }
}
