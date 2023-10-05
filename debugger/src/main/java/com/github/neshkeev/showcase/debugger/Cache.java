package com.github.neshkeev.showcase.debugger;

import java.util.*;

public class Cache {
    private static ArrayList<Integer> numbers;

    static ArrayList<Integer> getNumbers() {
        if (numbers == null) initFish();
        return numbers;
    }

    private static void initFish() {
        numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    }
}
