package com.github.neshkeev.showcase.solid.openclose;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoodSumHandlerFunctionalTest {

    @Test
    public void testBasicSum() {
        var goodSumHandler = new GoodSumHandlerFunctional();
        int sum = goodSumHandler.sum(List.of(1, -1, 2, -2, 3, -3));
        assertThat(sum, CoreMatchers.is(6));
    }

    @Test
    public void testConsumerProcessor() {
        var goodSumHandler = new GoodSumHandlerFunctional();
        List<Integer> values = List.of(1, -1, 2, -2, 3, -3);

        // int total = 0;
        final AtomicInteger total = new AtomicInteger(0);
        goodSumHandler.consumerProcessor(values, value -> {
            if (value < 0) return;
            // total += value;
            total.addAndGet(value);
        });

        assertThat(total.get(), CoreMatchers.is(6));
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