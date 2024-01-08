package com.github.neshkeev.showcase.threading.collections;

import java.util.function.Consumer;

public interface ConcurrentModificationHandler {
    void process(Consumer<Integer> handler);

    void append(Integer number);

    void prepend(Integer number);
}