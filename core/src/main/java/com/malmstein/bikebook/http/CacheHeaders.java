package com.malmstein.bikebook.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class CacheHeaders {

    private final Map<String, String> headers = new HashMap<String, String>();

    public static CacheHeaders empty() {
        return new CacheHeaders();
    }

    public static CacheHeaders noCache() {
        return new CacheHeaders().withNoCache();
    }

    public static CacheHeaders onlyFromCache() {
        return new CacheHeaders().withOnlyFromCache();
    }

    public static CacheHeaders maxStale(final long time, final TimeUnit unit) {
        return new CacheHeaders().withMaxStale(time, unit);
    }

    public static CacheHeaders maxAge(final long time, final TimeUnit unit) {
        return new CacheHeaders().withMaxAge(time, unit);
    }

    public static CacheHeaders revalidate() {
        return new CacheHeaders().withRevalidatation();
    }

    private CacheHeaders() {
    }

    public CacheHeaders withNoCache() {
        headers.put("Cache-Control", "no-cache");
        return this;
    }

    public CacheHeaders withOnlyFromCache() {
        headers.put("Cache-Control", "only-if-cached");
        return this;
    }

    public CacheHeaders withMaxStale(final long time, final TimeUnit unit) {
        headers.put("Cache-Control", "max-stale=" + unit.toSeconds(time));
        return this;
    }

    public CacheHeaders withMaxAge(final long time, final TimeUnit unit) {
        headers.put("Cache-Control", "max-age=" + unit.toSeconds(time));
        return this;
    }

    public CacheHeaders withRevalidatation() {
        headers.put("Cache-Control", "max-age=0");
        return this;
    }

    void applyTo(final HttpRequest request) {
        for (final Map.Entry<String, String> header : headers.entrySet()) {
            request.header(header);
        }
    }

    @Override
    public String toString() {
        final Objects.ToStringHelper helper = Objects.toStringHelper(this);
        for (Map.Entry<String, String> header  : headers.entrySet()) {
            helper.add(header.getKey(), header.getValue());
        }
        return helper.toString();
    }
}
