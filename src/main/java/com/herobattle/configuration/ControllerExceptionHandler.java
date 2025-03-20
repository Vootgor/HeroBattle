package com.herobattle.configuration;

import com.herobattle.controller.dto.ErrorDto;
import com.herobattle.exception.EnemyNotFoundException;
import com.herobattle.exception.HeroNotFoundException;
import com.herobattle.exception.MinBelowZeroException;
import com.herobattle.exception.MinExceedsMaxException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handleThrowable(Throwable e) {

        switch (e) {
            case HeroNotFoundException hnf -> {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(
                    "Hero not found",
                    hnf.getMessage(),
                    LocalDateTime.now()
                ));
            }
            case EnemyNotFoundException enf -> {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(
                    "Enemy not found",
                    e.getMessage(),
                    LocalDateTime.now()
                ));
            }
            case MinExceedsMaxException mem ->{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(
                    "User input error",
                    mem.getMessage(),
                    LocalDateTime.now()
                ));
            }
            case MinBelowZeroException mbz ->{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(
                    "User input error",
                    mbz.getMessage(),
                    LocalDateTime.now()
                ));
            }
            default -> {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(
                    "Internal Server Error",
                    e.getMessage(),
                    LocalDateTime.now()
                ));
            }
        }
    }
}
