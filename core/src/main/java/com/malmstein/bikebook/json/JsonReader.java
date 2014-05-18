package com.malmstein.bikebook.json;

import java.io.InputStream;
import java.lang.reflect.Type;

public interface JsonReader {
    <T> T fromJson(InputStream input, Class<T> clazz);

    <T> T fromJson(InputStream input, Type type);
}
