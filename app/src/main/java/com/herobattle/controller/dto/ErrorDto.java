package com.herobattle.controller.dto;

import java.time.LocalDateTime;

public record ErrorDto(
    String error,
    String message,
    LocalDateTime timestamp
) {

}
