package com.github.neshkeev.showcase.solid.liskovsubstitution;

public class GoodHandler {

    public interface Handleable {
        void process(GoodHandler handler);
    }

    public <T> void printFirst(Iterable<T> values) {
        if (values == null) return;
        final var it = values.iterator();

        if (!it.hasNext()) return;
        System.out.println(it.next());
    }

    public <T> void printAll(Iterable<T> values) {
        for (var value : values) {
            System.out.println(value);
        }
    }

    public void handle(Handleable handleable) {
        handleable.process(this);
    }
}
