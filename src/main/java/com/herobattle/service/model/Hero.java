package com.herobattle.service.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hero {
    private UUID id;
    private String name;
    private Integer hp;
    private Integer baseDamage;
}
