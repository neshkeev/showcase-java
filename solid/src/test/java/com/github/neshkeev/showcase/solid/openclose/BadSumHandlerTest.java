package com.github.neshkeev.showcase.solid.openclose;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class BadSumHandlerTest {

    @Test
    public void test() {
        BadSumHandler badSumHandler = new BadSumHandler();
        String sum = badSumHandler.sum(List.of(1, -1, 2, -2, 3, -3));
        assertThat(sum, CoreMatchers.is("Total is: 6"));
    }

}