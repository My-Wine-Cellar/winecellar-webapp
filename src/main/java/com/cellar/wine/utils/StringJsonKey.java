package com.cellar.wine.utils;

import com.github.cliftonlabs.json_simple.JsonKey;

public class StringJsonKey implements JsonKey {

    private final String str;

    public StringJsonKey(String str) {
        this.str = str;
    }

    @Override
    public String getKey() {
        return str;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
