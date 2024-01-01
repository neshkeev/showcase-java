package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedStaticMutexHandler {
    private volatile int counter;

    public SynchronizedStaticMutexHandler(int counter) {
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
