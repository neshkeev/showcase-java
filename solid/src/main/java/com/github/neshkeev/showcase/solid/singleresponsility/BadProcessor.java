package com.github.neshkeev.showcase.solid.singleresponsility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class BadProcessor {

    static List<Integer> process(@SuppressWarnings("SameParameterValue") String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        List<Integer> sizes = new ArrayList<>(lines.size());
        for (String line : lines) {
            sizes.add(line.length());
        }
        return sizes;
    }
}
