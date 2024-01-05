package com.github.neshkeev.showcase.threading.pool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class ThreadPoolDemoTest {
    private ThreadPoolDemo threadPoolDemo;

    @BeforeEach
    public void setUp() {
        threadPoolDemo = new ThreadPoolDemo();
    }

    @Test
    public void testCachedPool() throws ExecutionException, InterruptedException {
        runThreads(__ -> threadPoolDemo.cachedThread());
    }

    @Test
    public void testFixedPool() throws ExecutionException, InterruptedException {
        runThreads(__ -> threadPoolDemo.fixedThread());
    }

    @Test
    public void testSinglePool() throws ExecutionException, InterruptedException {
        runThreads(__ -> threadPoolDemo.singleThread());
    }

    @Test
    public void testWorkStealingPool() throws ExecutionException, InterruptedException {
        runThreads(__ -> threadPoolDemo.workStealingThread());
    }

    @Test
    public void testScheduledPool() throws ExecutionException, InterruptedException {
        runThreads(i -> threadPoolDemo.scheduledThread(Duration.ofSeconds(i)));
    }

    private void runThreads(final IntFunction<? extends Future<?>> createThread) throws InterruptedException, ExecutionException {
        var threads = IntStream
                .range(0, 10)
                .mapToObj(createThread)
                .toList();

        for (Future<?> thread : threads) {
            thread.get();
        }
    }
}