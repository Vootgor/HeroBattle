package com.herobattle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.herobattle.service.model.NarratorComments;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class NarratorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NarratorService narratorService;

    @BeforeEach
    void setUp() {
        narratorService = new NarratorService(restTemplate);
        Field baseUriField;
        try {
            baseUriField = NarratorService.class.getDeclaredField("baseUri");
            baseUriField.setAccessible(true);
            baseUriField.set(narratorService, "http://localhost:8082");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set baseUri", e);
        }
    }

    @Test
    void shouldReturnNarratorComments_whenApiCallSucceeds() {

        String heroName = "hero";
        String weapon = "sword";
        String background = "forest";
        String clothes = "armor";

        NarratorComments expectedComments = new NarratorComments("Intro", "Attack", "Defeat",
            "Win");
        String expectedUrl = "http://localhost:8082/api/v1/narrator?heroName=hero&weapon=sword&background=forest&clothes=armor";

        when(restTemplate.getForObject(eq(expectedUrl), eq(NarratorComments.class)))
            .thenReturn(expectedComments);

        NarratorComments actualComments = narratorService.getNarratorComments(heroName, weapon, background, clothes);

        assertEquals(expectedComments, actualComments);
    }
}