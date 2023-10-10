package com.github.neshkeev.showcase.solid.singleresponsility;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BadProcessorTest {

    @Test
    public void test() throws IOException {
        BadProcessor.process("pom.xml").forEach(System.out::println);
    }

}