package com.github.neshkeev.showcase.solid.liskovsubstitution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodHandlerTest {

    private GoodHandler goodHandler;

    @BeforeEach
    public void setup() {
        goodHandler = new GoodHandler();
    }

    @Test
    void testPrintFirstList() {
        final var list = List.of("Hello, World!");
        goodHandler.printFirst(list);
    }

    @Test
    void testPrintFirstMap() {
        final var map = Map.of("hello", "world");
        goodHandler.printFirst(map.keySet());
        goodHandler.printFirst(map.values());
    }

    @Test
    void testPrintAll() {
        final var set = Set.of("hello", "world");
        goodHandler.printAll(set);
    }

    @Test
    void testHandleEmployeeFrontend() {
        final var frontend = new Employee.Frontend();
        goodHandler.handle(frontend);
    }

    @Test
    void testHandleEmployeeAccountant() {
        final var accountant = new Employee.Accountant();
        goodHandler.handle(accountant);
    }

    @Test
    void testHandleNumber() {
        final var number = new HandleableNumberWrapper(Float.POSITIVE_INFINITY);
        goodHandler.handle(number);
    }

    @Test
    void testHandleTypeSafe() {
        @SuppressWarnings("unused")
        final var list = List.of();
//        goodHandler.handle(list);
    }

    static final class HandleableNumberWrapper implements GoodHandler.Handleable {

        private final Number number;

        HandleableNumberWrapper(Number number) {
            this.number = number;
        }

        @Override
        public void process(GoodHandler handler) {
            System.out.println("It's a number and it's value is: " + number);
        }
    }
}