package com.herobattle.service.model;

import java.util.List;

public record BattleLog(
    List<String> actionLog,
    String result
) {

}
