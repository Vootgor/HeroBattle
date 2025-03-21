package com.herobattle.exception;

import java.util.UUID;

public class HeroNotFoundException extends RestException {

    public HeroNotFoundException(UUID id) {
        super("Hero not found", "Hero not found with id: " + id);
    }
}
