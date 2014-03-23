package com.malmstein.bikebook;

import android.app.Application;
import android.content.Context;

import com.malmstein.bikebook.http.OkConnectionFactory;
import com.malmstein.bikebook.http.SawickiHttpClient;
import com.malmstein.bikebook.json.GsonJsonReader;
import com.novoda.notils.logger.simple.Log;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BikeBookApplication extends Application {

    private static Context context;

    private SawickiHttpClient httpClient;
    private GsonJsonReader jsonReader;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initHttp();
        initJsonReader();


        Logger.getLogger("").setLevel(android.util.Log.isLoggable("http", android.util.Log.DEBUG) ? Level.FINE : Level.INFO);
    }

    private void initHttp(){
        httpClient = new SawickiHttpClient(new OkConnectionFactory(createOkHttp()));
    }

    private OkHttpClient createOkHttp() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        try {
            final HttpResponseCache cache = new HttpResponseCache(getCacheDir(), 10 * 1024 * 1024);
            okHttpClient.setResponseCache(cache);
        } catch (IOException e) {
            Log.e("unable to set http cache", e);
        }
        return okHttpClient;
    }

    private void initJsonReader(){
        jsonReader = new GsonJsonReader();
    }
}
