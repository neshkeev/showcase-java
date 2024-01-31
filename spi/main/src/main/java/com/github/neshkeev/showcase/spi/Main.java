package com.github.neshkeev.showcase.spi;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<NameGenerator> loader = ServiceLoader.load(NameGenerator.class);
        for (NameGenerator generator : loader) {
            System.out.println(generator.generate());
        }
    }
}