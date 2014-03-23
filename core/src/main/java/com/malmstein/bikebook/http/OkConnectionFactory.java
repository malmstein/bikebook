package com.malmstein.bikebook.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/**
 * A {@link com.github.kevinsawicki.http.HttpRequest.ConnectionFactory connection factory} which uses OkHttp.
 * <p/>
 * Call {@link com.github.kevinsawicki.http.HttpRequest#setConnectionFactory(com.github.kevinsawicki.http.HttpRequest.ConnectionFactory)} with an instance of
 * this class to enable.
 */
public final class OkConnectionFactory implements HttpRequest.ConnectionFactory {
    private final OkHttpClient client;

    public OkConnectionFactory(final OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.client = client;
    }

    public HttpURLConnection create(final URL url) {
        return client.open(url);
    }

    public HttpURLConnection create(final URL url, final Proxy proxy) {
        throw new UnsupportedOperationException(
                "Per-connection proxy is not supported. Use OkHttpClient's setProxy instead.");
    }

    public HttpResponseCache getCache() {
        return (HttpResponseCache) client.getOkResponseCache();
    }
}