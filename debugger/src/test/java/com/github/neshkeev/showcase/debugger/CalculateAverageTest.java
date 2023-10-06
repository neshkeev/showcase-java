package com.github.neshkeev.showcase.debugger;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CalculateAverageTest {

    @Test
    void testFindAverage() {
        double average = CalculateAverage.findAverage(1, 2, 3);

        assertThat(average, CoreMatchers.is(2.0));
    }
}