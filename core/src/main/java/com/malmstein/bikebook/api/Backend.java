package com.malmstein.bikebook.api;


import com.malmstein.bikebook.http.CacheHeaders;
import com.malmstein.bikebook.http.HttpClient;
import com.malmstein.bikebook.json.ApiRequest;
import com.malmstein.bikebook.json.JsonReader;
import com.malmstein.bikebook.json.responses.Index;
import com.malmstein.bikebook.json.responses.Manufacturer;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Backend {

    private final static Logger LOGGER = Logger.getLogger("Backend");

    public enum EndPoint {

        PRODUCTION("Production", "http://bikebook.io", 1.0d);

        private final String name;
        private final URL url;
        private final double version;

        EndPoint(final String name, final String url, final double version) {
            try {
                this.name = name;
                this.url = new URL(url);
                this.version = version;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return name;
        }

        public URL getUrl() {
            return url;
        }

        public double getVersion() {
            return version;
        }

        public static EndPoint from(final String endpoint) {
            for (final EndPoint value : values()) {
                if (value.url.toString().equals(endpoint)) {
                    return value;
                }
            }
            return PRODUCTION;
        }
    }

    private final HttpClient httpClient;
    private final URL manifestUrl;
    private final JsonReader jsonReader;

    public Backend(final URL baseUrl, final HttpClient httpClient, final JsonReader jsonReader) {
        this.httpClient = checkNotNull(httpClient);
        this.manifestUrl = createUrl(baseUrl.toString() + manifestPath());
        this.jsonReader = checkNotNull(jsonReader);
    }

    private static String manifestPath() {
        return "assets/index.json";
    }

    public boolean isManifestOutdated() {
        return httpClient.get(manifestUrl(), CacheHeaders.onlyFromCache()) == null;
    }

    public List<Manufacturer> readManifest() {
        return readManifest(CacheHeaders.maxStale(15, TimeUnit.MINUTES));
    }

    private List<Manufacturer> readManifest(final CacheHeaders cacheHeaders) {
        return readManifest(cacheHeaders, false);
    }

    /**
     * @param cacheHeaders additional headers to tweak caching
     * @param isRetry      true if we are retrying after some failure condition, e.g. trying to get some stale cache data.
     *                     When already retrying, we don't try again.
     * @return read manifest
     * @throws RuntimeException on any error conditions
     */
    private List<Manufacturer> readManifest(final CacheHeaders cacheHeaders, final boolean isRetry) {
        try {
            return getAndParse(manifestUrl, cacheHeaders, Index.class).getManufacturers();
        } catch (RuntimeException e) {
            if (isRetry) {
                throw e;
            } else {
                LOGGER.log(Level.WARNING, "while reading manifest", e);
                LOGGER.info("try falling back to ANY stale cached manifest version...");
                return readManifest(CacheHeaders.maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS), true);
            }
        }
    }

    private <T> T getAndParse(final URL url, final CacheHeaders cacheHeaders, final Class<T> responseClass) {
        InputStream input = null;
        try {
            input = httpClient.get(url, cacheHeaders);
            if (input == null) {
                throw new RuntimeException("null response from " + url + " with cache " + cacheHeaders);
            }
            return jsonReader.fromJson(input, responseClass);
        } catch (RuntimeException e) {
            throw new RuntimeException("while parsing data at " + url, e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Testing only!
     *
     * @return the url the manifest is loaded from
     */
    public URL manifestUrl() {
        return manifestUrl;
    }

    private static URL createUrl(final String urlAsString) {
        try {
            return new URL(urlAsString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T queryApi(final String requestUrl, final ApiRequest request, final Class<T> responseClass) {
        final String encodedJson = encode(request.toJson());
        return jsonReader.fromJson(httpClient.get(createUrl(requestUrl + "?json=" + encodedJson)), responseClass);
    }

    private static String encode(final String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}



