package com.github.neshkeev.showcase.debugger;

public class CalculateAverage {

    static double findAverage(int... input) {
        double result = 0;
        for (var s : input) {
            result += s;
        }
        return result / input.length;
    }
}
