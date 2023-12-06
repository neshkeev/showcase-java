package com.github.neshkeev.showcase.solid.liskovsubstitution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

public class BadHandlerTest {

    private BadHandler badHandler;

    @BeforeEach
    public void setup() {
        badHandler = new BadHandler();
    }

    @Test
    public void testPrintFirst() {
        final var values = new ArrayList<String>();
        values.add("Hello");
        values.add("World");
        badHandler.printFirst(values);
    }

    @Test
    public void testPrintAll() {
        final var values = Set.of("Hello", ", ", "World", "!");

        badHandler.printAll(values);
    }

    @Test
    public void testGenericHandlerEmployee() {
        badHandler.handle(new Employee.Programmer());
    }

    @Test
    public void testGenericHandlerInteger() {
        badHandler.handle(42);
    }

    @Test
    public void testGenericHandlerUnknown() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> badHandler.handle(this)
        );
    }
}