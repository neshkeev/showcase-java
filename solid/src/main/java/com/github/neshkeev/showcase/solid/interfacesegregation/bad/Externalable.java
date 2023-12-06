package com.github.neshkeev.showcase.solid.interfacesegregation.bad;

import java.util.function.Consumer;

public interface Externalable<T> {

    void read(Consumer<T> consumer);
    int write(T obj);
}
