package com.github.neshkeev.showcase.spi.impl;

import com.github.neshkeev.showcase.spi.NameGenerator;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomNameGenerator implements NameGenerator {

    public static final int[] SYMBOLS = IntStream.range('A', 'Z').toArray();

    @Override
    public String generate() {
        final var sb = new StringBuilder();
        final var random = new Random();
        var length = random.nextInt(10) + 5;
        while (length -- > 0) {
            int symbolIndex = random.nextInt(0, SYMBOLS.length - 1);
            sb.append((char)SYMBOLS[symbolIndex]);
        }
        return sb.toString();
    }
}
