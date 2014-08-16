package com.malmstein.bikebook;

import android.app.Application;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BikeBookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.getLogger("").setLevel(android.util.Log.isLoggable("http", android.util.Log.DEBUG) ? Level.FINE : Level.INFO);
    }


}
