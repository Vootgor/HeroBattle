package com.herobattle.controller.dto;

import java.util.UUID;

public record HeroDto(
    UUID id,
    String name,
    Integer hp,
    Integer baseDamage
) {

}
