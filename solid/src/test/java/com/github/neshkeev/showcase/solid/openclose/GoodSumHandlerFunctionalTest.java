package com.github.neshkeev.showcase.solid.openclose;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoodSumHandlerFunctionalTest {

    @Test
    public void testBasicSum() {
        var goodSumHandler = new GoodSumHandlerFunctional();
        int sum = goodSumHandler.sum(List.of(1, -1, 2, -2, 3, -3));
        assertThat(sum, CoreMatchers.is(6));
    }

    @Test
    public void testNegativeSum() {
        var goodSumHandler = new GoodSumHandlerFunctional();
        int sum = goodSumHandler.sum(
                List.of(1, -1, 2, -2, 3, -3),
                value -> value < 0
        );
        assertThat(sum, CoreMatchers.is(-6));
    }

    @Test
    public void testSumEvenAndGreaterThanOne() {
        var goodSumHandler = new GoodSumHandlerFunctional();
        int sum = goodSumHandler.sum(
                List.of(1, -1, 2, -2, 3, -3),
                value -> value % 2 == 0 && value > 1
        );
        assertThat(sum, CoreMatchers.is(2));
    }
}