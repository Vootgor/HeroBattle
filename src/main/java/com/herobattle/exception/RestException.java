package com.herobattle.exception;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {

    private final String error;

    public RestException(String error, String message) {
        super(message);
        this.error = error;
    }
}
