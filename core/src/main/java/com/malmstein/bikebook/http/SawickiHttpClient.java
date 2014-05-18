package com.malmstein.bikebook.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.squareup.okhttp.HttpResponseCache;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

public class SawickiHttpClient implements HttpClient {

    private static final Logger logger = Logger.getLogger("http");
    private final HttpResponseCache responseCache;

    public SawickiHttpClient(final HttpRequest.ConnectionFactory connectionFactory) {
        HttpRequest.setConnectionFactory(connectionFactory);
        if (connectionFactory instanceof OkConnectionFactory) {
            responseCache = ((OkConnectionFactory) connectionFactory).getCache();
        } else {
            responseCache = null;
        }
    }

    @Override
    public InputStream get(final URL url) {
//        logger.info(logger.getName() + " GET at " + url);
        return get(url, CacheHeaders.empty());
    }

    @Override
    public InputStream get(final URL url, final CacheHeaders headers) {
        try {
            final HttpRequest request = HttpRequest.get(url);
            headers.applyTo(request);
            if (request.code() >= 300) {
                return null;
            }
            return request.stream();
        } finally {
            debugLogCache();
        }
    }

    private void debugLogCache() {
        if (responseCache == null) {
            return;
        }
        logger.info("cache requests: " + responseCache.getRequestCount());
        logger.info("cache network : " + responseCache.getNetworkCount());
        logger.info("cache hits    : " + responseCache.getHitCount());
    }
}
