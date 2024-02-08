package com.github.neshkeev.showcase.springplugins.spanishweb;

import com.github.neshkeev.showcase.springplugins.api.ConfigProvider;

public class SpanishConfigProvider implements ConfigProvider {

    @Override
    public Class<?> getConfig() {
        return SpanishConfig.class;
    }
}