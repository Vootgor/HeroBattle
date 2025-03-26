package com.herobattle.controller.request;

public record FightRequest(
        int minEnemies,
        int maxEnemies,
        String weapon,
        String backGround,
        String clothes
) {
}
