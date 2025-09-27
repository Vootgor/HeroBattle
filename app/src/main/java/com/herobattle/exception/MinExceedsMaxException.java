package com.herobattle.exception;

public class MinExceedsMaxException extends RestException {

    public MinExceedsMaxException() {
        super("User input error", "minEnemies cannot exceed maxEnemies");
    }
}
