package com.github.neshkeev.showcase.threading.mutex;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SynchronizedHandlerTest {

    @Test
    public void testBadHandler() throws InterruptedException {
        final var handler = new SynchronizedBadHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

//        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testExternalMutex() throws InterruptedException {
        final var handler = new SynchronizedExternalMutexHandler(new Object(), 1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testThisMutex() throws InterruptedException {
        final var handler = new SynchronizedThisMutexHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testThisMethodMutex() throws InterruptedException {
        final var handler = new SynchronizedThisMethodHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testStaticMutex() throws InterruptedException {
        final var handler = new SynchronizedStaticMutexHandler(1000);

        incDecConcurrent(handler::inc, handler::dec);

        assertThat(handler.getCounter(), is(equalTo(1000)));
    }

    @Test
    public void testStaticMethod() throws InterruptedException {
        SynchronizedStaticMethodHandler.counter = 1000;

        incDecConcurrent(SynchronizedStaticMethodHandler::inc, SynchronizedStaticMethodHandler::dec);

        assertThat(SynchronizedStaticMethodHandler.getCounter(), is(equalTo(1000)));
    }

    private static void incDecConcurrent(final Runnable inc, final Runnable dec) throws InterruptedException {
        final var threads = new ArrayList<Thread>(2000);

        for (int i = 0; i < 1000; i++) {
            Thread incThread = new Thread(inc);
            Thread decThread = new Thread(dec);

            threads.add(incThread);
            threads.add(decThread);

            incThread.start();
            decThread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}