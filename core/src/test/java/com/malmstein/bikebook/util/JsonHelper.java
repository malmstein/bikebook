package com.malmstein.bikebook.util;

import java.io.InputStream;

public class JsonHelper {

    private JsonHelper() {
    }

    public static InputStream convertResourceFilePathToInputStream(String path) {
        return getApiResourceStream(path);
    }

    private static InputStream getApiResourceStream(String name) {
        return JsonHelper.class.getResourceAsStream("/com/malmstein/bikebook/api/" + name);
    }

    public static InputStream index() {
        return getApiResourceStream("index.json");
    }

}
