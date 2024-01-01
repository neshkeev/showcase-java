package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedThisMutexHandler {
    private volatile int counter;

    public SynchronizedThisMutexHandler(int counter) {
        this.counter = counter;
    }

    public void inc() {
        synchronized (this) {
            counter++;
        }
    }

    public void dec() {
        synchronized (this) {
            counter--;
        }
    }

    public int getCounter() {
        synchronized (this) {
            return counter;
        }
    }
}
