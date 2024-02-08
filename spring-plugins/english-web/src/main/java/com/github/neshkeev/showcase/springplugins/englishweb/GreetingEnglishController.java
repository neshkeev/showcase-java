package com.github.neshkeev.showcase.springplugins.englishweb;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/en")
public class GreetingEnglishController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, %s!".formatted(name);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(GreetingEnglishController.class + " constructed.");
    }
}