package com.github.neshkeev.showcase.debugger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DataRaceTest {

    static final List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

    @Test
    public void test() throws InterruptedException {
        Thread t = new Thread(() -> addIfAbsent(42), "Background");
        t.start();
        addIfAbsent(42);
        t.join();
        assertThat(syncList, hasSize(1));
    }

    private static void addIfAbsent(int x) {
        if (!syncList.contains(x)) {
            syncList.add(x);
        }
    }
}