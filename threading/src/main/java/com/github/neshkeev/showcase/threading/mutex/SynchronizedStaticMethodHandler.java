package com.github.neshkeev.showcase.threading.mutex;

public class SynchronizedStaticMethodHandler {
    static volatile int counter;

    public synchronized static void inc() {
        counter++;
    }

    public synchronized static void dec() {
        counter--;
    }

    public synchronized static int getCounter() {
        return counter;
    }
}
