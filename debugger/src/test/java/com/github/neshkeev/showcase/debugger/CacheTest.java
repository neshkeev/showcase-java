package com.github.neshkeev.showcase.debugger;

import org.junit.jupiter.api.Test;

public class CacheTest {

    @Test
    void testGetNumbers() {
        System.out.println(Cache.getNumbers());
        System.out.println(Cache.obtainNumbers());
    }
}