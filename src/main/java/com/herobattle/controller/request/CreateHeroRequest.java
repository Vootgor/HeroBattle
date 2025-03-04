package com.herobattle.controller.request;

public record CreateHeroRequest(
    String name,
    Integer hp,
    Integer baseDamage
) {

}
