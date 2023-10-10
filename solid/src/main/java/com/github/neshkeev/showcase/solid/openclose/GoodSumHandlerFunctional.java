package com.github.neshkeev.showcase.solid.openclose;

import java.util.List;
import java.util.function.Predicate;

public final class GoodSumHandlerFunctional {
    int sum(List<Integer> values) {
        return sum(values, value -> value > 0);
    }

    int sum(List<Integer> values, Predicate<Integer> predicate) {
        int s = 0;
        for (Integer value : values) {
            if (predicate.test(value)) {
                s += value;
            }
        }

        return s;
    }
}
