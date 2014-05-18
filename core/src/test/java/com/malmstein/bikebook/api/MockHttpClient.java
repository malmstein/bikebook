package com.malmstein.bikebook.api;

import com.malmstein.bikebook.http.CacheHeaders;
import com.malmstein.bikebook.http.HttpClient;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public final class MockHttpClient implements HttpClient {

    private static final Logger LOGGER = Logger.getLogger(MockHttpClient.class.getSimpleName());
    private final HttpClient client;
    private final URL baseUrl;
    private final URL replaceUrl;

    public MockHttpClient(final HttpClient client, final URL baseUrl, final URL replaceUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
        this.replaceUrl = replaceUrl;
    }

    @Override
    public InputStream get(final URL url) {
        return client.get(map(url));
    }

    @Override
    public InputStream get(final URL url, final CacheHeaders headers) {
        return client.get(map(url), headers);
    }

    private URL map(final URL url) {
        try {
            final String replace = url.toString().replace(baseUrl.toString(), replaceUrl.toString());
            LOGGER.info("mapping " + url.toString() + " to "+ replace);
            LOGGER.info("baseUrl = " + baseUrl);
            LOGGER.info("replaceUrl = "+ replaceUrl);
            return new URL(replace);
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
