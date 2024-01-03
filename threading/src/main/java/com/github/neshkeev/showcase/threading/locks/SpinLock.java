package com.github.neshkeev.showcase.threading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class SpinLock {
    private final Lock lock = new ReentrantLock();

    public void lock() {
        //noinspection StatementWithEmptyBody
        while(!lock.tryLock()) {
        }
    }

    public void unlock() {
        lock.unlock();
    }
}
