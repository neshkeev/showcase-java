package com.github.neshkeev.showcase.threading.sync;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class SemaphoreDemoTest {
    private SemaphoreDemo semaphoreDemo;

    @BeforeEach
    void setUp() {
        semaphoreDemo = new SemaphoreDemo(2);
    }

    @Test
    public void criticalSection() throws InterruptedException {
        var list = IntStream.range(0, 5)
                .mapToObj(n -> new Thread(semaphoreDemo::criticalSection) {{
                    setName("Thread-" + n);
                }})
                .peek(Thread::start)
                .toList();
        for (Thread thread : list) {
            thread.join();
        }
    }
}