package com.herobattle.configuration;

import com.herobattle.controller.dto.ErrorDto;
import com.herobattle.exception.*;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handleThrowable(Throwable e) {
        return switch (e) {
            case HeroNotFoundException hnf -> createResponseEntity(hnf, HttpStatus.NOT_FOUND);
            case EnemyNotFoundException enf -> createResponseEntity(enf, HttpStatus.NOT_FOUND);
            case MinExceedsMaxException mem -> createResponseEntity(mem, HttpStatus.BAD_REQUEST);
            case MinBelowZeroException mbz -> createResponseEntity(mbz, HttpStatus.BAD_REQUEST);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto("Internal Server Error", e.getMessage(), LocalDateTime.now()));
        };
    }

    private ResponseEntity<ErrorDto> createResponseEntity(RestException restEx, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ErrorDto(restEx.getError(), restEx.getMessage(), LocalDateTime.now()));
    }
}
