package com.github.neshkeev.showcase.debugger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataRace {
    static final List<Integer> a = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> addIfAbsent(17));
        t.start();
        addIfAbsent(17);
        t.join();
        System.out.println(a);
    }

    private static void addIfAbsent(int x) {
        synchronized (DataRace.class) {
            if (!a.contains(x)) {
                a.add(x);
            }
        }
    }
}