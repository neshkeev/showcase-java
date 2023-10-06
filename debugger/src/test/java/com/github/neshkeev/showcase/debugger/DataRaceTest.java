package com.github.neshkeev.showcase.debugger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class DataRaceTest {

    private static final List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

    @Test
    public void test() throws InterruptedException {
        Thread t = new Thread(DataRaceTest::addIfAbsent, "Background");
        t.start();
        addIfAbsent();
        t.join();
        assertThat(syncList, hasSize(1));
    }

    private static void addIfAbsent() {
        if (!syncList.contains(42)) {
            syncList.add(42);
        }
    }
}