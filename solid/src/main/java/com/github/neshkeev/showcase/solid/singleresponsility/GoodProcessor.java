package com.github.neshkeev.showcase.solid.singleresponsility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class GoodProcessor {
    static List<String> getContent(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    static List<Integer> process(List<String> content) {
        List<Integer> sizes = new ArrayList<>(content.size());
        for (String line : content) {
            sizes.add(line.length());
        }
        return sizes;
    }
}
