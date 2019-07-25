/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.stetho.Stetho;
import com.scan.barcode.data.entities.User;
import com.scan.barcode.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.BuildConfig;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-16.
 */
public class ScanApplication extends DaggerApplication {

    private static ScanApplication mInstance;
    public int WIDTH_SCREEN;
    public int HEIGHT_SCREEN;

    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        stetho();
        WIDTH_SCREEN = getResources().getDisplayMetrics().widthPixels;
        HEIGHT_SCREEN = getResources().getDisplayMetrics().heightPixels;
        mInstance = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // Init multi dex
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends dagger.android.DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static synchronized ScanApplication getInstance() {
        return mInstance;
    }

    /**
     * The integration with the Chrome DevTools frontend is implemented using a client/server protocol
     * which the Stetho software provides for your application. Once your application is integrated,
     * simply navigate to chrome://inspect and click "Inspect" to get started!
     */
    private void stetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}