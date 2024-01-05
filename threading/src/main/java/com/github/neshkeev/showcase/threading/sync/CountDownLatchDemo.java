package com.github.neshkeev.showcase.threading.sync;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private final CountDownLatch cdl;

    public CountDownLatchDemo(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public void criticalSection() {
        try {
            cdl.await();
            System.out.println(Thread.currentThread().getName() + " is in the critical section");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void nonProtectedCriticalSection() {
        System.out.println(Thread.currentThread().getName() + " is in the non protected critical section");
    }
}
