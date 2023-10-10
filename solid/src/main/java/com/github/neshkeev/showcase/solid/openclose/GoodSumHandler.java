package com.github.neshkeev.showcase.solid.openclose;

import java.util.List;

public class GoodSumHandler {
    int sum(List<Integer> values) {
        int s = 0;
        for (Integer value : values) {
            if (allow(value)) {
                s += value;
            }
        }

        return s;
    }

    protected boolean allow(Integer value) {
        return value > 0;
    }
}
