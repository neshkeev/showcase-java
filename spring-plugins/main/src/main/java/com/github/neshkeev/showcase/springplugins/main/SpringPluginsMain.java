package com.github.neshkeev.showcase.springplugins.main;

import com.github.neshkeev.showcase.springplugins.api.ConfigProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ServiceLoader;

@SpringBootApplication
public class SpringPluginsMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringPluginsMain.class)
                .sources(getPluginConfigs())
                .run(args);
    }

    private static Class<?>[] getPluginConfigs() {
        return ServiceLoader.load(ConfigProvider.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .map(ConfigProvider::getConfig)
                .toArray(Class<?>[]::new);
    }
}