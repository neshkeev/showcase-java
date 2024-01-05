package com.github.neshkeev.showcase.threading.pool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ThreadPoolDemo {

    private final ExecutorService cachedThreadPool;
    private final ExecutorService fixedThreadPool;
    private final ExecutorService singleThreadExecutor;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ExecutorService workStealingPool;

    public ThreadPoolDemo() {
        cachedThreadPool = Executors.newCachedThreadPool();
        fixedThreadPool = Executors.newFixedThreadPool(2);
        singleThreadExecutor = Executors.newSingleThreadExecutor();
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
        workStealingPool = Executors.newWorkStealingPool();
    }

    public Future<?> cachedThread() {
        return submitThread(cachedThreadPool);
    }

    public Future<?> fixedThread() {
        return submitThread(fixedThreadPool);
    }

    public Future<?> singleThread() {
        return submitThread(singleThreadExecutor);
    }

    public Future<?> workStealingThread() {
        return submitThread(workStealingPool);
    }

    public ScheduledFuture<?> scheduledThread(Duration delay) {
        System.out.println("Current time: " + LocalDateTime.now());
        return scheduledExecutorService.schedule(
                () -> System.out.println("Executing at " + LocalDateTime.now()),
                delay.toMillis(),
                TimeUnit.MILLISECONDS
        );
    }

    private Future<?> submitThread(ExecutorService executor) {
        return executor.submit(() -> {
            System.out.println("Executing in " + executor);
            try {
                Thread.sleep(Duration.ofSeconds(2).toMillis());
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
