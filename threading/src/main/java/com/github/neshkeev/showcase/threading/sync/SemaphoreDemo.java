package com.github.neshkeev.showcase.threading.sync;

import java.time.Duration;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private final Semaphore semaphore;

    public SemaphoreDemo(final int permits) {
        semaphore = new Semaphore(permits);
    }

    public void criticalSection() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is in");
            Thread.sleep(Duration.ofSeconds(2).toMillis());
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " is out");
            semaphore.release();
        }
    }
}
