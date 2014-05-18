package com.malmstein.bikebook.util;

import com.malmstein.bikebook.http.HttpClient;
import com.malmstein.bikebook.http.OkConnectionFactory;
import com.malmstein.bikebook.http.SawickiHttpClient;
import com.squareup.okhttp.OkHttpClient;

public final class HttpClientUtil {

    private HttpClientUtil() {
    }

    public static HttpClient createSimpleHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return new SawickiHttpClient(new OkConnectionFactory(client));
    }

}
