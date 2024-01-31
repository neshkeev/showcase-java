package com.github.neshkeev.showcase.spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameGeneratorTest {
    @Test
    void listAllGeneratorsTest() {
        final ServiceLoader<NameGenerator> loader = ServiceLoader.load(NameGenerator.class);
        for (NameGenerator nameGenerator : loader) {
            System.out.println("Found: " + nameGenerator.getClass().getName());
        }
    }

    @Test
    void generateNameTest() {
        final ServiceLoader<NameGenerator> loader = ServiceLoader.load(NameGenerator.class);

        assertAll(
                () -> assertTrue(loader.iterator().hasNext()),
                () -> assertThat(loader.iterator().next().generate(), is(not(emptyString())))
        );
    }
}
