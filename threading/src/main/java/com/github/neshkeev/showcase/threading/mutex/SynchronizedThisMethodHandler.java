package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedThisMethodHandler {
    private volatile int counter;

    public SynchronizedThisMethodHandler(int counter) {
        this.counter = counter;
    }

    public synchronized void inc() {
        counter++;
    }

    public synchronized void dec() {
        counter--;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
