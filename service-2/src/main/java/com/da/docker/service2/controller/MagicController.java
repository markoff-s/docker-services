package com.da.docker.service2.controller;

import com.da.docker.service2.model.Counter;
import com.da.docker.service2.repository.CounterRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MagicController {

    @Autowired
    private CounterRepositoty counterRepository;

    @GetMapping("/magic-number")
    public Integer getMagicNumber() {
        Counter counter = counterRepository.findAll().get(0);

        return counter.getCounter();
    }
}
