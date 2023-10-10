package com.github.neshkeev.showcase.solid.openclose;

import java.util.List;

public class BadSumHandler {
    String sum(List<Integer> values) {
        int s = 0;
        for (Integer value : values) {
            if (value > 0) {
                s += value;
            }
        }

        return "Total is: " + s;
    }
}
