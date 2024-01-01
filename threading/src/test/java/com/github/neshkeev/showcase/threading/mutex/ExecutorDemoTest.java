package com.github.neshkeev.showcase.threading.mutex;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ExecutorDemoTest {
    @Test
    public void testBadHandler() {
        final var handler = new SynchronizedBadHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

//        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testExternalMutex() {
        final var handler = new SynchronizedExternalMutexHandler(new Object(), 1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testThisMutex() {
        final var handler = new SynchronizedThisMutexHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testThisMethodMutex() {
        final var handler = new SynchronizedThisMethodHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testStaticMutex() {
        final var handler = new SynchronizedStaticMutexHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testStaticMethod() {
        SynchronizedStaticMethodHandler.counter = 1000;

        incDecConcurrent(SynchronizedStaticMethodHandler::inc, SynchronizedStaticMethodHandler::dec);

        assertThat(SynchronizedStaticMethodHandler.getCounter(), is(equalTo(1000)));
    }

    private static void incDecConcurrent(final Runnable inc, final Runnable dec) {
        final var executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(inc);
            executor.submit(dec);
        }
        executor.shutdown();
    }
}