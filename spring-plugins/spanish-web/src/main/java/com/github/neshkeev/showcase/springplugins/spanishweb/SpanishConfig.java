package com.github.neshkeev.showcase.springplugins.spanishweb;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpanishConfig {

    @PostConstruct
    public void postConstruct() {
        System.out.println(SpanishConfig.class + " constructed.");
    }
}