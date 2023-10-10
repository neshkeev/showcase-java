package com.github.neshkeev.showcase.solid.openclose;

import java.util.ArrayList;
import java.util.List;

public class BadSumHandler {
    String sum(List<Integer> values) {
        List<Integer> positives = new ArrayList<>(values.size());
        for (Integer value : values) {
            if (value > 0) {
                positives.add(value);
            }
        }

        int s = 0;
        for (Integer positive : positives) {
            s += positive;
        }

        return "Total is: " + s;
    }
}
