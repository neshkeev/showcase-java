package com.github.neshkeev.showcase.solid.openclose;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoodSumHandlerTest {

    @Test
    public void testBasicSum() {
        GoodSumHandler goodSumHandler = new GoodSumHandler.GoodSumPositivesHandler();
        int sum = goodSumHandler.sum(List.of(1, -1, 2, -2, 3, -3));
        assertThat(sum, CoreMatchers.is(6));
    }

    @Test
    public void testNegativeSum() {
        GoodSumHandler goodSumHandler = new GoodSumNegativesHandler();
        int sum = goodSumHandler.sum(List.of(1, -1, 2, -2, 3, -3));
        assertThat(sum, CoreMatchers.is(-6));
    }

    private static final class GoodSumNegativesHandler extends GoodSumHandler {
        @Override
        protected boolean allow(Integer value) {
            return value < 0;
        }
    }
}