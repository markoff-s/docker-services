package com.da.docker.service1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MagicController {

    @Value("${api.svc2.magic-number-url}")
    private String svc2Url;

    @GetMapping("/multiply/{number}")
    public long calculateMagicNumber(@PathVariable long number) {
        RestTemplate restTemplate = new RestTemplate();
        Integer multiplier = restTemplate.getForObject(svc2Url, Integer.class);

        return number * multiplier;
    }
}
