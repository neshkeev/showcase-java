package com.github.neshkeev.showcase.solid.interfacesegregation.good;

import java.util.function.Consumer;

public interface Readable<T> {
    void read(Consumer<T> consumer);
}
