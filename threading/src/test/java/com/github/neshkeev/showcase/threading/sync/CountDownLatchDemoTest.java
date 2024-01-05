package com.github.neshkeev.showcase.threading.sync;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchDemoTest {
    private CountDownLatchDemo countDownLatchDemo;
    private CountDownLatch cdl;

    @BeforeEach
    void setUp() {
        cdl = new CountDownLatch(1);
        countDownLatchDemo = new CountDownLatchDemo(cdl);
    }

    @Test
    public void testNonProtectedCriticalSection() throws InterruptedException {
        var list = IntStream.range(0, 5)
                .mapToObj(n -> new Thread(countDownLatchDemo::nonProtectedCriticalSection) {{
                    setName("Thread-" + n);
                }})
                .peek(Thread::start)
                .toList();
        System.out.println("Allowing Threads to Advance");

        for (Thread thread : list) {
            thread.join();
        }
    }

    @Test
    public void testCriticalSection() throws InterruptedException {
        var list = IntStream.range(0, 5)
                .mapToObj(n -> new Thread(countDownLatchDemo::criticalSection) {{
                    setName("Thread-" + n);
                }})
                .peek(Thread::start)
                .toList();
        System.out.println("Allowing Threads to Advance");

        cdl.countDown();

        for (Thread thread : list) {
            thread.join();
        }
    }
}