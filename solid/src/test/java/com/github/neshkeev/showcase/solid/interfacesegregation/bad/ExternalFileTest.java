package com.github.neshkeev.showcase.solid.interfacesegregation.bad;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExternalFileTest {
    private static final String HELLO_WORLD = "Hello, World";

    private static ExternalFile externalFile;

    @BeforeAll
    public static void setup() throws IOException {
        var file = File.createTempFile("external", "txt");
        file.deleteOnExit();

        externalFile = new ExternalFile(file);
    }

    @Test
    @Order(1)
    public void testWrite() {
        final int written = write(externalFile, HELLO_WORLD);

        assertThat(written, is(HELLO_WORLD.getBytes().length));
    }

    @Test
    @Order(2)
    public void testRead() {
        final String content = read(externalFile);

        assertThat(content, is(HELLO_WORLD));
    }

    @SuppressWarnings("SameParameterValue")
    private int write(final Externalable<String> externalable, final String content) {
        return externalable.write(content);
    }

    @SuppressWarnings("SameParameterValue")
    private String read(final Externalable<String> readable) {
        final var sb = new StringBuilder();
        readable.read(sb::append);

        return sb.toString();
    }
}