package com.malmstein.bikebook.http;

import java.io.InputStream;
import java.net.URL;

public interface HttpClient {

    InputStream get(URL url);

    InputStream get(URL url, CacheHeaders headers);
}
