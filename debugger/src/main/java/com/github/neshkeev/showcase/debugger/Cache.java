package com.github.neshkeev.showcase.debugger;

import java.util.ArrayList;
import java.util.Arrays;

public class Cache {
    private static ArrayList<Integer> numbers;

    static ArrayList<Integer> getNumbers() {
        if (numbers == null) setupNumbers();
        return numbers;
    }

    static ArrayList<Integer> obtainNumbers() {
        if (numbers == null) initNumbers();
        return numbers;
    }

    private static void setupNumbers() {
        numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        System.out.println("setupNumbers");
    }

    private static void initNumbers() {
        numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        System.out.println("initNumber");
    }

}
