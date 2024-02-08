package com.github.neshkeev.showcase.springplugins.spanishweb;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class GreetingSpanishController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        return "¡Hola %s!".formatted(name);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(GreetingSpanishController.class + " constructed.");
    }
}