package com.github.neshkeev.showcase.springplugins.englishweb;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnglishConfig {

    @PostConstruct
    public void postConstruct() {
        System.out.println(EnglishConfig.class + " constructed.");
    }
}