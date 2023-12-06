package com.github.neshkeev.showcase.solid.interfacesegregation.good;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopyFileContentGoodTest {
    private static final String HELLO_WORLD = "Hello, World";

    private static FileReadableWriteable fileFrom;
    private static FileReadableWriteable fileTo;

    @BeforeAll
    public static void setup() throws IOException {
        fileFrom = createExternalFile();
        fileTo = createExternalFile();

        fileFrom.write(HELLO_WORLD);
    }

    private static FileReadableWriteable createExternalFile() throws IOException {
        final var file = File.createTempFile("external", "txt");
        file.deleteOnExit();

        return new FileReadableWriteable(file);
    }

    @Test
    public void testCopy() {
        copy(fileFrom, fileTo);

        final String content = read(fileTo);

        assertThat(content, is(HELLO_WORLD));
    }

    private void copy(final Readable<String> from, final Writeable<String> to) {
        from.read(to::write);
    }

    private String read(final Readable<String> from) {
        final var sb = new StringBuilder();
        from.read(sb::append);

        return sb.toString();
    }
}