package com.malmstein.bikebook.api;

import android.content.Context;

import com.malmstein.bikebook.R;
import com.malmstein.bikebook.http.OkConnectionFactory;
import com.malmstein.bikebook.http.SawickiHttpClient;
import com.malmstein.bikebook.json.GsonJsonReader;
import com.novoda.notils.logger.simple.Log;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BikeBookApiFactory {

    public static BikeBook defaultClient(Context context) throws MalformedURLException {
        return new BikeBook(new URL(context.getString(R.string.base_url)),
                new SawickiHttpClient(new OkConnectionFactory(createOkHttp(context))),
                new GsonJsonReader());
    }

    private static OkHttpClient createOkHttp(Context context) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        try {
            final File httpCacheDir = getHttpCacheDir(context);
            httpCacheDir.mkdirs();
            final HttpResponseCache cache = new HttpResponseCache(httpCacheDir, 10 * 1024 * 1024);
            okHttpClient.setResponseCache(cache);
        } catch (final IOException e) {
            Log.e("unable to set http cache", e);
        }
        return okHttpClient;
    }

    private static File getHttpCacheDir(Context context) {
        return new File(context.getCacheDir(), "http/");
    }

}
