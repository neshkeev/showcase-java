package com.github.neshkeev.showcase.threading.locks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StackMapHandlerTest {
    private ExecutorService executor;

    @BeforeEach
    public void beforeEach() {
        executor = Executors.newCachedThreadPool();
    }

    @AfterEach
    public void afterEach() {
        executor.shutdown();
    }

    @Test
    public void testBadHandler() {
        final var stackMapDemo = new StackMapBadHandler();

        executor.submit(() -> stackMapDemo.add("Hello", "World"));
        executor.submit(() -> stackMapDemo.add("One", "Two"));

        Future<String> one = executor.submit(stackMapDemo::popRecent);
        Future<String> hello = executor.submit(stackMapDemo::popRecent);

        assertAll(
                () -> assertThat(one.get(), is(equalTo("Two"))),
                () -> assertThat(hello.get(), is(equalTo("World")))
        );
    }

    @Test
    public void testGoodHandler() {
        final var stackMapDemo = new StackMapGoodHandler();

        executor.submit(() -> stackMapDemo.add("Hello", "World"));
        executor.submit(() -> stackMapDemo.add("One", "Two"));

        Future<String> one = executor.submit(stackMapDemo::popRecent);
        Future<String> hello = executor.submit(stackMapDemo::popRecent);

        assertAll(
                () -> assertThat(one.get(), is(equalTo("Two"))),
                () -> assertThat(hello.get(), is(equalTo("World")))
        );
    }
}