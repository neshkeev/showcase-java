package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedStaticMutexHandler {
    private volatile int counter;

    public SynchronizedStaticMutexHandler(int counter) {
        this.counter = counter;
    }

    public void inc() {
        synchronized (SynchronizedStaticMutexHandler.class) {
            counter++;
        }
    }

    public void dec() {
        synchronized (SynchronizedStaticMutexHandler.class) {
            counter--;
        }
    }

    public int getCounter() {
        synchronized (SynchronizedStaticMutexHandler.class) {
            return counter;
        }
    }
}
