package com.github.neshkeev.showcase.threading.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class ConcurrentModificationBadHandlerTest {
    private CountDownLatch cdl;

    @BeforeEach
    void setUp() {
        cdl = new CountDownLatch(1);
    }

    @Test
    public void testBadHandler() throws InterruptedException {
        final var handler = new ConcurrentModificationBadHandler();
        readerWriter(handler);
    }

    @Test
    public void testGoodHandler() throws InterruptedException {
        final var handler = new ConcurrentModificationGoodHandler();
        readerWriter(handler);
    }

    private void readerWriter(ConcurrentModificationHandler handler) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            handler.append(i);
        }

        final var writer = new Thread(() -> {
            try {
                cdl.await();
                for (int i = 10; i < 20; i++) {
                    final var now = LocalDateTime.now();
                    //noinspection StatementWithEmptyBody
                    while (now.plusNanos(100_000_000L).isBefore(LocalDateTime.now())) {
                    }
                    handler.prepend(i);
                }
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final Thread reader = new Thread(() -> {
            try {
                cdl.await();
                handler.process(x -> {
                    final var now = LocalDateTime.now();
                    //noinspection StatementWithEmptyBody
                    while (now.plusNanos(100_000_000L).isBefore(LocalDateTime.now())) {
                    }
                    System.out.println(x);
                });
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        writer.start();
        reader.start();

        cdl.countDown();

        writer.join();
        reader.join();
    }
}