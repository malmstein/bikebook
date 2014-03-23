package com.malmstein.bikebook.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.squareup.okhttp.HttpResponseCache;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
        logger.info(logger.getName() + " GET at " + url);
        return get(url, CacheHeaders.empty());
    }

    @Override
    public InputStream get(final URL url, final CacheHeaders headers) {
        try {
            final HttpRequest request = HttpRequest.get(url);
            headers.applyTo(request);
            logResponseHeaders(request.headers());
            if (request.code() >= 300) {
                return null;
            }
            return wrapStreamForLogging(request.stream(), Level.FINE);
        } finally {
            debugLogCache();
        }
    }

    private static InputStream wrapStreamForLogging(final InputStream inputStream, final Level logLevel) {
        if (logger.isLoggable(logLevel)) {
            return new LoggingInputStream(inputStream, logger, logLevel);
        }
        return inputStream;
    }

    private static void logResponseHeaders(final Map<String, List<String>> headers) {
        for (final Map.Entry<String, List<String>> header : headers.entrySet()) {
            logger.fine("response header: " + header.getKey() + " : " + header.getValue());
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
