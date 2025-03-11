package com.herobattle.service;

import com.herobattle.service.model.NarratorComments;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class NarratorService {

    private final RestTemplate restTemplate;

//    @Value("${narrator.api.url}")
    @Value("${narrator.api.url:http://localhost:8082}")
    private String baseUri;

    public NarratorComments getNarratorComments(String heroName,
        String weapon,
        String background,
        String clothes) {

        String url = UriComponentsBuilder.fromUriString(baseUri)
            .path("/api/v1/narrator")
            .queryParam("heroName", heroName)
            .queryParam("weapon", weapon)
            .queryParam("background", background)
            .queryParam("clothes", clothes)
            .toUriString();

        return restTemplate.getForObject(url, NarratorComments.class);
    }

}
