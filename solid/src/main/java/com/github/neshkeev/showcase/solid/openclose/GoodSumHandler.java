package com.github.neshkeev.showcase.solid.openclose;

import java.util.List;

public abstract class GoodSumHandler {
    int sum(List<Integer> values) {
        if (values == null) return 0;
        if (values.isEmpty()) return 0;

        int s = 0;
        for (Integer value : values) {
            if (allow(value)) {
                s += value;
            }
        }

        return s;
    }

    protected abstract boolean allow(Integer value);

    public static final class GoodSumPositivesHandler extends GoodSumHandler {
        protected boolean allow(Integer value) {
            return value > 0;
        }
    }
}
