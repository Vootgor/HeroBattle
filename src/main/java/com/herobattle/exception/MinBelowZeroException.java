package com.herobattle.exception;

public class MinBelowZeroException extends RuntimeException {

    public MinBelowZeroException() {
        super("minEnemies cannot be less than 0");
    }
}
