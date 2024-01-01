package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedExternalMutexHandler {
    private final Object mutex;
    private volatile int counter;

    public SynchronizedExternalMutexHandler(Object mutex, int counter) {
        this.mutex = mutex;
        this.counter = counter;
    }

    public void inc() {
        synchronized (mutex) {
            counter++;
        }
    }

    public void dec() {
        synchronized (mutex) {
            counter--;
        }
    }

    public int getCounter() {
        synchronized (mutex) {
            return counter;
        }
    }
}
