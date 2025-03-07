package com.herobattle.controller.request;

public record CreateEnemyRequest(
    String name,
    Integer hp,
    Integer baseDamage
) {

}
