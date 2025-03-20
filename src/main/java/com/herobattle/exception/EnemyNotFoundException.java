package com.herobattle.exception;

import java.util.UUID;

public class EnemyNotFoundException extends RestException {

    public EnemyNotFoundException(UUID id) {
        super("Enemy not found", "Enemy not found with id: " + id);
    }
}
