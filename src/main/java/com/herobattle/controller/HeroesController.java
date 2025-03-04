package com.herobattle.controller;

import com.herobattle.controller.dto.HeroDto;
import com.herobattle.controller.request.CreateHeroRequest;
import com.herobattle.service.HeroService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/heroes")
public class HeroesController {

    private final HeroService heroService;

    @PostMapping("/")
    public HeroDto createHero(@RequestBody CreateHeroRequest request) {
        return heroService.createHero(request);
    }

    @DeleteMapping("/{id}")
    public HeroDto deleteHero(@PathVariable UUID id) {
        return heroService.deleteHero(id);
    }

    @GetMapping("/{id}")
    public HeroDto getHero(@PathVariable UUID id) {}
}
