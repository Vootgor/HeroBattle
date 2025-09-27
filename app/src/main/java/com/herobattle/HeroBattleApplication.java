package com.herobattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class HeroBattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroBattleApplication.class, args);
    }
}
