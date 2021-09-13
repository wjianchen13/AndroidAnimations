package com.cold.androidanimations;

import android.app.Application;
import android.net.http.HttpResponseCache;

import java.io.File;
import java.io.IOException;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            File cacheDir = new File(getCacheDir(), "http");
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
