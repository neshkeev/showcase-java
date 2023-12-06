package com.github.neshkeev.showcase.solid.interfacesegregation.bad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class ExternalFile implements Externalable<String> {

    private final File file;

    public ExternalFile(File file) {
        this.file = file;
    }

    @Override
    public void read(Consumer<String> consumer) {
        try (var fis = new FileInputStream(file)) {
            consumer.accept(new String(fis.readAllBytes()));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int write(String obj) {
        try (var fos = new FileOutputStream(file)) {
            fos.write(obj.getBytes());
            return obj.getBytes().length;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
