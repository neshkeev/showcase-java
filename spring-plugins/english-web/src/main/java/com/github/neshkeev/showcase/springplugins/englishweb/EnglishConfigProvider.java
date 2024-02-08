package com.github.neshkeev.showcase.springplugins.englishweb;

import com.github.neshkeev.showcase.springplugins.api.ConfigProvider;

public class EnglishConfigProvider implements ConfigProvider {

    @Override
    public Class<?> getConfig() {
        return EnglishConfig.class;
    }
}