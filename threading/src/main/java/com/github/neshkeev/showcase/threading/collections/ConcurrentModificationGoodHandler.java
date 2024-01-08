package com.github.neshkeev.showcase.threading.collections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class ConcurrentModificationGoodHandler implements ConcurrentModificationHandler {
    private final List<Integer> numbers = new CopyOnWriteArrayList<>();

    @Override
    public void process(Consumer<Integer> handler) {
        numbers.forEach(handler);
    }

    @Override
    public void append(Integer number) {
        numbers.add(number);
    }

    @Override
    public void prepend(Integer number) {
        numbers.add(0, number);
    }
}
