/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ndngan
 * Created on 4/6/18.
 */
@Module
public abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract Context bindContext(Application application);
}