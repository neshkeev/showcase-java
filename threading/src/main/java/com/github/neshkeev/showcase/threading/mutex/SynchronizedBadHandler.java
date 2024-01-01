package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedBadHandler {
    private int counter;

    public SynchronizedBadHandler(int counter) {
        this.counter = counter;
    }

    public void inc() {
        counter++;
    }

    public void dec() {
        counter--;
    }

    @SuppressWarnings("unused")
    public int getCounter() {
        return counter;
    }
}
