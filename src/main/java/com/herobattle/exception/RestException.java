package com.herobattle.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestException extends RuntimeException {

    private String error;

    public RestException(String error, String message) {
        super(message);
        this.error = error;
    }
}
