package com.herobattle.exception;

import java.util.UUID;

public class EnemyNotFoundException extends RuntimeException {

    public EnemyNotFoundException(UUID id) {
        super("Enemy not found with id: " + id);
    }
}
