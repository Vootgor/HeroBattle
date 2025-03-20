package com.herobattle.exception;

public class MinBelowZeroException extends RestException {

    public MinBelowZeroException() {
        super("User input error", "minEnemies cannot be less than 0");
    }
}
