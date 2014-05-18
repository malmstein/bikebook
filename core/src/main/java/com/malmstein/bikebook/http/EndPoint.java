package com.malmstein.bikebook.http;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkNotNull;

public enum EndPoint {

        PRODUCTION("Production", "http://bikebook.io");

        private final String name;

        private final URL url;

        EndPoint(final String name, final String url) {
            try {
                this.name = checkNotNull(name);
                this.url = new URL(url);
            } catch (final MalformedURLException e) {
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

    }