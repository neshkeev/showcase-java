package com.github.neshkeev.showcase.debugger;

import org.junit.jupiter.api.Test;

public class ConditionalTest {

    @Test
    public void test() {
        f();
        g();
    }

    static void f() {
        System.out.println("f");
        h();
    }
    static void g() {
        System.out.println("g");
        h();
    }
    static void h() {
        System.out.println("h");
    }
}
