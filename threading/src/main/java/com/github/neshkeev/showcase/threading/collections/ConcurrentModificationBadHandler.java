package com.github.neshkeev.showcase.threading.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConcurrentModificationBadHandler implements ConcurrentModificationHandler {
    private final List<Integer> numbers = new ArrayList<>();

    // strong consistency: ACID: atomicity, consistency, isolation, durability
    // eventual consistency: BASE: basic availability, soft state, eventual consistency
    @Override
    public void process(Consumer<Integer> handler) { // 1 -> ..., 2 -> ..., | 3 -> ...
        numbers.forEach(handler);
    }

    @Override
    public void append(Integer number) { // 1, 2, 3, 4
        numbers.add(number);
    }

    @Override
    public void prepend(Integer number) {
        numbers.add(0, number);
    }
}
