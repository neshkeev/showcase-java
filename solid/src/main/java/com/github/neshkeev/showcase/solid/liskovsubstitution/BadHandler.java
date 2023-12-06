package com.github.neshkeev.showcase.solid.liskovsubstitution;

import java.util.ArrayList;
import java.util.Set;

public class BadHandler {

    public void printFirst(ArrayList<String> values) {
        if (values != null && !values.isEmpty()) {
            System.out.println(values.get(0));
        }
    }

    public void printAll(Set<String> values) {
        for (String value : values) {
            System.out.println(value);
        }
    }

    public void handle(Object obj) {
        if (obj instanceof Employee) {
            System.out.println("It's Employee");
        }
        else if (obj instanceof Number) {
            System.out.println("It's Employee");
        }
        else {
            throw new IllegalArgumentException("Object '%s' not supported".formatted(obj.getClass()));
        }
    }
}
