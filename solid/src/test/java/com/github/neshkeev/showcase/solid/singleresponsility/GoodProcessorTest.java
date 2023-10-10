package com.github.neshkeev.showcase.solid.singleresponsility;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GoodProcessorTest {

    @Test
    public void testGetContent() throws IOException {
        List<String> content = GoodProcessor.getContent(Path.of("pom.xml"));
        content.forEach(System.out::println);
    }

    @Test
    public void testProcess() {
        List<Integer> sizes = GoodProcessor.process(List.of(""));
        assertAll(
                () -> assertThat(sizes.size(), CoreMatchers.is(1)),
                () -> assertThat(sizes.get(0), CoreMatchers.is(0))
        );
    }

}