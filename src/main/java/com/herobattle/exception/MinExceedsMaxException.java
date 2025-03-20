package com.herobattle.exception;

public class MinExceedsMaxException extends RuntimeException {

    public MinExceedsMaxException() {
        super("minEnemies cannot exceed maxEnemies");
    }
}
