package com.github.neshkeev.showcase.solid.interfacesegregation.bad;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopyFileContentTest {
    private static final String HELLO_WORLD = "Hello, World";

    private static ExternalFile externalFileFrom;
    private static ExternalFile externalFileTo;

    @BeforeAll
    public static void setup() throws IOException {
        externalFileFrom = createExternalFile();
        externalFileTo = createExternalFile();

        externalFileFrom.write(HELLO_WORLD);
    }

    private static ExternalFile createExternalFile() throws IOException {
        var file = File.createTempFile("external", "txt");
        file.deleteOnExit();

        return new ExternalFile(file);
    }

    @Test
    public void testCopy() {
        copy(externalFileFrom, externalFileTo);

        final String content = read(externalFileTo);

        assertThat(content, is(HELLO_WORLD));
    }

    private void copy(Externalable<String> from, Externalable<String> to) {
        from.read(to::write);
    }

    private String read(Externalable<String> from) {
        final var sb = new StringBuilder();
        from.read(sb::append);

        return sb.toString();
    }
}