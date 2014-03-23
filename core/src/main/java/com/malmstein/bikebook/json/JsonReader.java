package com.malmstein.bikebook.json;

import java.io.InputStream;

public interface JsonReader {
    <T> T fromJson(InputStream input, Class<T> clazz);
}
