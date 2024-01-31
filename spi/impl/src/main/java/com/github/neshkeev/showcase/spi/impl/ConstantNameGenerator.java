package com.github.neshkeev.showcase.spi.impl;

import com.github.neshkeev.showcase.spi.NameGenerator;

public class ConstantNameGenerator implements NameGenerator {

    @Override
    public String generate() {
        return "Hello, World!";
    }
}