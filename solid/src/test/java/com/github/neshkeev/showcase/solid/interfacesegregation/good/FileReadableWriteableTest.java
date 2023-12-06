package com.github.neshkeev.showcase.solid.interfacesegregation.good;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileReadableWriteableTest {
    private static final String HELLO_WORLD = "Hello, World";

    private static FileReadableWriteable fileRW;

    @BeforeAll
    public static void setup() throws IOException {
        var file = File.createTempFile("external", "txt");
        file.deleteOnExit();

        fileRW = new FileReadableWriteable(file);
    }

    @Test
    @Order(1)
    public void testWrite() {
        final int written = write(fileRW, HELLO_WORLD);

        assertThat(written, is(HELLO_WORLD.getBytes().length));
    }

    @Test
    @Order(2)
    public void testRead() {
        final String read = read(fileRW);

        assertThat(read, is(HELLO_WORLD));
    }

    @Test
    @Order(3)
    public void testTransform() {
        write(fileRW, "World!");

        final String expected = transform(fileRW, "Hello, "::concat);

        final String actual = read(fileRW);

        Assertions.assertAll(
                () -> assertThat(actual, is(expected)),
                () -> assertThat(actual, is("Hello, World!"))
        );
    }

    @SuppressWarnings("SameParameterValue")
    private int write(final Writeable<String> writeable, final String content) {
        return writeable.write(content);
    }

    private String read(final Readable<String> readable) {
        final var sb = new StringBuilder();
        readable.read(sb::append);

        return sb.toString();
    }

    private <
                CONTENT,
                T extends Readable<CONTENT> & Writeable<CONTENT>
            > CONTENT transform(
                    T target,
                    final Function<CONTENT, CONTENT> transformer
    ) {
        final AtomicReference<CONTENT> buf = new AtomicReference<>();
        target.read(buf::set);

        final CONTENT newValue = transformer.apply(buf.get());

        target.write(newValue);

        return newValue;
    }
}